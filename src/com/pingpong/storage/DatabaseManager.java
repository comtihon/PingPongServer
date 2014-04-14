package com.pingpong.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingpong.core.Logger;
import com.pingpong.server.Config;
import io.orchestrate.client.*;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by tihon on 05.04.14.
 */
public class DatabaseManager extends Storage {

    private final String pingerCollection;
    private final Client client;

    @Override
    public long getAndIncr(String key) {
        long result = super.getAndIncr(key);  //getAndIncr result from cache
        Logger.d("Result from cache %d", result);
        if (result == 1) {  //cache record is new. Search database for old ping values
            long pingsFromDb = loadPingValue(key);
            Logger.d("Result from db  " + pingsFromDb);
            if (pingsFromDb > 0) {
                super.incr(key, pingsFromDb + 1);    //updates cache with database info
                result += pingsFromDb;
            }
        }

        return result;
    }

    @Override
    public void set(String key, long value) {
        super.set(key, value);
    }

    @Override
    public void terminate() throws IOException {
        client.stop();
    }

    public DatabaseManager(StorageInt component) {
        super(component);
        String apiKey = Config.getInstance().getProperty("db_api_key");
        pingerCollection = Config.getInstance().getProperty("db_ping_collection");
        if (apiKey == null || apiKey.isEmpty()
                || pingerCollection == null || pingerCollection.isEmpty())
            throw new RuntimeException("Orchestrate db is not configured correctly!");
        client = new ClientBuilder(apiKey).build();
    }

    /**
     * Save pings number to database. Is called only by DataLoaderJob
     *
     * @param key Pinger's uid
     */
    @Override
    public void savePingValue(String key) {
        if (client == null)
            return;

        long value = super.getAndClear(key);

        Logger.i("Save %d for %s to DB", value, key);
        KvStoreOperation kvStoreOp = new KvStoreOperation(pingerCollection, key, toJson(value));
        client.execute(kvStoreOp);    // execute the operation

        // execute the operation
        client.execute(kvStoreOp);
    }

    /**
     * Got previously saved ping values from database or 0 if none found.
     *
     * @param key Pinger's uid to search for
     * @return pings number of 0
     */
    public long loadPingValue(String key) {
        if (key == null)
            return 0;
        KvFetchOperation<String> kvFetchOp = new KvFetchOperation<>(pingerCollection, key, String.class);

        Future<KvObject<String>> kvObjectFuture = client.execute(kvFetchOp);  // execute the operation
        KvObject<String> kvObject = null;
        try {
            kvObject = kvObjectFuture.get(3, TimeUnit.SECONDS);  // wait for the result
        } catch (Exception e) {
            Logger.w("Error during waiting for Orchestrate. Reason:" + e.getMessage());
        }
        return kvObject == null ? 0 : fromJson(kvObject.getValue());
    }

    private String toJson(long value) {
        return "{\"ping\":" + value + "}";
    }

    private long fromJson(String json) {
        try {
            return new ObjectMapper().readTree(json).get("ping").asLong();
        } catch (IOException e) {
            Logger.w("Can't parse json: " + json + " Reason : " + e.getMessage());
            return 0;
        }
    }

}
