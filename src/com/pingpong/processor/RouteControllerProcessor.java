package com.pingpong.processor;

import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import com.pingpong.server.Config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tihon on 14.04.14.
 */
public class RouteControllerProcessor implements ControllerProcessor {

    private final String controllersPath;
    private Map<String, Method> controllerMap;

    public RouteControllerProcessor() {
        controllerMap = new HashMap<>();
        controllersPath = Config.getInstance().getProperty("controllers_path", "com.pingpong.controller");
    }

    @Override
    public void addController(String packetName, String className, String methodName) throws NoSuchMethodException, ClassNotFoundException {
        Class mainClass = Class.forName(controllersPath + "." + className);
        Method method = mainClass.getMethod(methodName, Request.class);
        controllerMap.put(packetName, method);
        Logger.i("Load controller: %s.%s for packets: %s", className, methodName, packetName);
    }

    @Override
    public Method getController(String packetName) {
        return controllerMap.get(packetName);
    }
}
