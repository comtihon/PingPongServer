package com.pingpong.cache;

import com.pingpong.server.Config;
import org.redisson.Redisson;

/**
 * Created by tihon on 05.04.14.
 */
public class RedissonImpl implements CacheImplementation {

    private final Redisson redisson;

    private static final class Holder {
        private static final RedissonImpl INSTANCE = new RedissonImpl();
    }

    public static RedissonImpl getInstance() {
        return Holder.INSTANCE;
    }

    private RedissonImpl() {
        int poolSize = Integer.valueOf(Config.getInstance().getProperty("cache_pool_size"));
        String nodes = Config.getInstance().getProperty("cache_nodes");

        org.redisson.Config config = new org.redisson.Config();
        config.setConnectionPoolSize(poolSize);

        for (String node : nodes.split(",")) {
            config.addAddress(node);
        }
        redisson = Redisson.create(config);
    }

    @Override
    public long incr(String key) {
        return redisson.getAtomicLong(key).incrementAndGet();
    }

    @Override
    public long getAndClear(String key) {
        return redisson.getAtomicLong(key).getAndSet(0);
    }

    @Override
    public void shutdown() {
        redisson.shutdown();
    }
}
