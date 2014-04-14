package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 14.04.14.
 */
public abstract class StorageImpl implements Storage {
    protected Storage storeComponent;

    public StorageImpl(Storage storage) {
        storeComponent = storage;
    }

    @Override
    public long get(String key) {
        return storeComponent.get(key);
    }

    @Override
    public void set(String key, long value) {
        storeComponent.set(key, value);
    }

    @Override
    public void terminate() throws IOException {
        storeComponent.terminate();
    }
}
