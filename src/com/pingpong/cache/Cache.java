package com.pingpong.cache;

import com.pingpong.core.Logger;

/**
 * Created by tihon on 05.04.14.
 */
public class Cache {

    public static CacheImplementation cacheImpl;

    /**
     * Increment the element value (must be a Number) by 1.
     *
     * @param key Element key
     * @return The new value
     */
    public static long incr(String key) {
        return cacheImpl.incr(key, 1);
    }

    private static CacheImplementation newCacheImplementation() {
        return RedissonImpl.getInstance();
    }

    /**
     * Initialize the cache system.
     */
    public static void init() {
        if (cacheImpl != null)
            cacheImpl.shutdown();
        cacheImpl = newCacheImplementation();
        Logger.i("[%s] is used as a cache provider.", cacheImpl == null ? "null" : cacheImpl.getClass().getName());
    }

    public static void shutdown() {
        cacheImpl.shutdown();
    }

}