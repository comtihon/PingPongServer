package com.pingpong.storage;

import com.pingpong.server.Config;
import org.redisson.Redisson;

import java.io.IOException;

/**
 * Created by tihon on 05.04.14.
 */
public class RedissonCache implements Storage {

    private final Redisson redisson;

    @Override
    public long get(String key) {
        return redisson.getAtomicLong(key).get();
    }

    @Override
    public void set(String key, long value) {

    }

    @Override
    public void terminate() throws IOException {

    }

    public RedissonCache() {
        int poolSize = Integer.valueOf(Config.getInstance().getProperty("cache_pool_size"));
        String nodes = Config.getInstance().getProperty("cache_nodes");

        org.redisson.Config config = new org.redisson.Config();
        config.setConnectionPoolSize(poolSize);

        for (String node : nodes.split(",")) {
            config.addAddress(node);
        }
        redisson = Redisson.create(config);
    }

    public long incr(String key) {
        return redisson.getAtomicLong(key).incrementAndGet();
    }

    public long incr(String key, long value) {
        return redisson.getAtomicLong(key).addAndGet(value);
    }

    public long getAndClear(String key) {
        return redisson.getAtomicLong(key).getAndSet(0);
    }

    public void shutdown() {
        redisson.shutdown();
    }
}
