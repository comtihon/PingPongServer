package com.pingpong.storage;

import java.io.IOException;

/**
 * Created by tihon on 10.04.14.
 */
public interface Storage {

    public long get(String key);
    public void set(String key, long value);
    public void terminate() throws IOException;
}
