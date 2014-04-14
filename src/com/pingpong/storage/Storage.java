package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 14.04.14.
 */
public abstract class Storage implements StorageInt {
    protected StorageInt storeComponent;

    public Storage(StorageInt storage) {
        storeComponent = storage;
    }

    @Override
    public long getAndIncr(String key) {
        return storeComponent.getAndIncr(key);
    }

    @Override
    public void set(String key, long value) {
        storeComponent.set(key, value);
    }

    @Override
    public void terminate() throws IOException {
        storeComponent.terminate();
    }

    public long incr(String key) {
        return storeComponent.incr(key);
    }

    public long incr(String key, long value) {
        return storeComponent.incr(key, value);
    }

    public long getAndClear(String key) {
        return storeComponent.getAndClear(key);
    }
}
