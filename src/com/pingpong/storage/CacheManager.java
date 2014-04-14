package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 05.04.14.
 */
public class CacheManager extends StorageImpl {

    public static RedissonCache cacheImpl;

    public CacheManager(Storage value) {
        super(value);
        if (cacheImpl != null)
            cacheImpl.shutdown();
        cacheImpl = new RedissonCache();
    }

    @Override
    public long get(String key) {
        return cacheImpl.get(key);
    }

    @Override
    public void set(String key, long value) {
        cacheImpl.set(key, value);
    }

    @Override
    public void terminate() throws IOException {
        cacheImpl.shutdown();
    }

    /**
     * Increment the element value (must be a Number) by 1.
     *
     * @param key Element key
     * @return The new value
     */
    public static long incr(String key) {
        return cacheImpl.incr(key);
    }

    public static long incr(String key, long value) {
        return cacheImpl.incr(key, value);
    }

    public static long getAndClear(String key) {
        return cacheImpl.getAndClear(key);
    }

}
