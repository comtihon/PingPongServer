package com.pingpong.controller;

import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import com.pingpong.packet.gen.*;
import com.pingpong.packet.gen.Error;
import com.pingpong.server.ProcessorContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tihon on 04.04.14.
 */
public class MainController {
    public static void processPacket(final Request packet) throws InvocationTargetException, IllegalAccessException {

        Method method = ProcessorContainer.getControllerProcessor().getController(packet.getType().toString());
        if (method == null) {
            Logger.w("Unknown packet type: " + packet.getType());
            Error.ErrorPacket error = Error.ErrorPacket.newBuilder()
                    .setCode(404)
                    .setDescr("Unknown packet type!")
                    .build();
            packet.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
        }
        else
            method.invoke(null, packet);
    }
}
