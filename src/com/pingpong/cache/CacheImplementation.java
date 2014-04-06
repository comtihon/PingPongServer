package com.pingpong.cache;

/**
 * Created by tihon on 05.04.14.
 */
public interface CacheImplementation {

    public long incr(String key);

    public long incr(String key, long value);

    public long getAndClear(String key);

    public void shutdown();
}
