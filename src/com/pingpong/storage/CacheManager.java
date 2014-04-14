package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 05.04.14.
 */
public class CacheManager extends Storage {

    public CacheManager(StorageInt value) {
        super(value);
    }

    @Override
    public long getAndIncr(String key) {
        return super.getAndIncr(key);
    }

    @Override
    public void set(String key, long value) {
        super.set(key, value);
    }

    @Override
    public void terminate() throws IOException {
        super.terminate();
    }

    @Override
    public void savePingValue(String key) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Increment the element value (must be a Number) by 1.
     *
     * @param key Element key
     * @return The new value
     */
    public long incr(String key) {
        return super.incr(key);
    }

    public long incr(String key, long value) {
        return super.incr(key, value);
    }

    public long getAndClear(String key) {
        return super.getAndClear(key);
    }

}
