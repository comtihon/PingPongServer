package com.pingpong.storage;

import com.pingpong.core.Logger;
import com.pingpong.server.Config;
import org.redisson.Redisson;

import java.io.IOException;

/**
 * Created by tihon on 05.04.14.
 */
public class RedissonCache implements StorageInt {

    private final Redisson redisson;

    public RedissonCache() {
        int poolSize = Integer.valueOf(Config.getInstance().getProperty("cache_pool_size"));
        String nodes = Config.getInstance().getProperty("cache_nodes");

        org.redisson.Config config = new org.redisson.Config();
        config.setConnectionPoolSize(poolSize);

        for (String node : nodes.split(",")) {
            config.addAddress(node);
        }
        redisson = Redisson.create(config);
        Logger.i("Redisson cache was initialized.");
    }

    @Override
    public long getAndIncr(String key) {
        return redisson.getAtomicLong(key).incrementAndGet();
    }

    @Override
    public void set(String key, long value) {
        redisson.getAtomicLong(key).set(value);
    }

    @Override
    public void terminate() throws IOException {
        redisson.shutdown();
    }

    @Override
    public void savePingValue(String key) {
        throw new UnsupportedOperationException("Unsupported. Trying to make cache do db work.");
    }

    @Override
    public long incr(String key) {
        return redisson.getAtomicLong(key).incrementAndGet();
    }

    @Override
    public long incr(String key, long value) {
        return redisson.getAtomicLong(key).addAndGet(value);
    }

    @Override
    public long getAndClear(String key) {
        return redisson.getAtomicLong(key).getAndSet(0);
    }

}
