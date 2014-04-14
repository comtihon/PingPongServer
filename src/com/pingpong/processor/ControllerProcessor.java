package com.pingpong.processor;

import java.lang.reflect.Method;

/**
 * Created by tihon on 14.04.14.
 */
public interface ControllerProcessor {
    public void addController(String packetName, String className, String methodName) throws NoSuchMethodException, ClassNotFoundException;

    public Method getController(String packetName);
}
