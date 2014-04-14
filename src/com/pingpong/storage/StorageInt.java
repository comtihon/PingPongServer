package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 10.04.14.
 */
public interface StorageInt {

    public long getAndIncr(String key);

    public void set(String key, long value);

    public void terminate() throws IOException;

    public void savePingValue(String key);

    public long incr(String key);

    public long incr(String key, long value);

    public long getAndClear(String key);
}
