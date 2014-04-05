package com.pingpong.manager;

import com.pingpong.core.Logger;
import com.pingpong.server.Config;
import io.orchestrate.client.*;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by tihon on 05.04.14.
 */
public class DatabaseManager {

    private final String pingerCollection;
    private final Client client;

    private final CopyOnWriteArrayList<String> cacheUids;   //Uids of Pingers, that are in cache and need to be saved in database

    private static class SingletonHolder {
        private static final DatabaseManager instance = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return SingletonHolder.instance;
    }

    private DatabaseManager() {
        String apiKey = Config.getInstance().getProperty("db_api_key");
        pingerCollection = Config.getInstance().getProperty("db_ping_collection");
        if (apiKey == null || apiKey.isEmpty()
                || pingerCollection == null || pingerCollection.isEmpty())
            throw new RuntimeException("Orchestrate db is not configured correctly!");
        client = new ClientBuilder(apiKey).build();
        cacheUids = new CopyOnWriteArrayList<>();
    }

    /**
     * Add connected Pinger to array. This array is used by DataLoaderJob to synchronize data between
     * database and cache.
     *
     * @param uid
     */
    public void addPinger(String uid) {
        if (cacheUids != null) {
            if (!cacheUids.contains(uid))
                cacheUids.add(uid);
        }
    }

    public CopyOnWriteArrayList<String> getCacheUids() {
        return cacheUids;
    }

    /**
     * Got previously saved ping values from database or 0 if none found.
     *
     * @param key Pinger's uid to search for
     * @return pings number of 0
     */
    public long getPingValue(String key) {
        if (pingerCollection == null || key == null)
            return 0;
        KvFetchOperation<Long> kvFetchOp = new KvFetchOperation<>(pingerCollection, key, Long.class);

        Future<KvObject<Long>> kvObjectFuture = client.execute(kvFetchOp);  // execute the operation
        KvObject<Long> kvObject = null;
        try {
            kvObject = kvObjectFuture.get(3, TimeUnit.SECONDS);  // wait for the result
        } catch (Exception e) {
            Logger.w("Error during waiting for Orchestrate. Reason:" + e.getMessage());
        }
        return kvObject == null ? 0 : kvObject.getValue();
    }

    /**
     * Save pings number to database. Is called only by DataLoaderJob
     *
     * @param key   Pinger's uid
     * @param value number of pings from cache
     */
    public void setPingValue(String key, Long value) {
        if (client == null)
            return;
        KvStoreOperation kvStoreOp = new KvStoreOperation(pingerCollection, key, value);
        client.execute(kvStoreOp);    // execute the operation
    }

    public void stopClient() throws IOException {
        if (client != null)
            client.stop();
    }

}
