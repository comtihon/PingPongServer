package com.pingpong.manager;

import com.pingpong.controller.PingController;
import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import com.pingpong.packet.gen.*;
import com.pingpong.packet.gen.Error;

import static com.pingpong.packet.gen.Error.ErrorPacket;

/**
 * Created by tihon on 04.04.14.
 */
public class PacketManager {
    public static void processPacket(final Request packet) {
        switch (packet.getType()) {
            case Ping:
                PingController.processPing(packet);
                break;
            default:
                Logger.w("Unknown packet type: " + packet.getType());
                ErrorPacket error = Error.ErrorPacket.newBuilder()
                        .setCode(404)
                        .setDescr("Unknown packet type!")
                        .build();
                packet.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
        }
    }
}
