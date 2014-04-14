package com.pingpong.processor;

/**
 * Created by tihon on 14.04.14.
 */
public interface LoaderProcessor{
    public void syncCache();

    public void registerPinger(String uid);
}
