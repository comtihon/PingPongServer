package com.pingpong.controller;

import com.pingpong.model.Request;
import com.pingpong.packet.gen.Packet;

import static com.pingpong.packet.gen.Error.ErrorPacket;
import static com.pingpong.packet.gen.Ping.PingPacket;
import static com.pingpong.packet.gen.Pong.PongPacket;

/**
 * Created by tihon on 04.04.14.
 */
public class PingController {
    public static void processPing(final Request request) {
        PingPacket ping = request.getPing();
        if (ping == null) {
            ErrorPacket error = ErrorPacket.newBuilder()
                    .setCode(400)
                    .setDescr("Bad packet's body!")
                    .build();
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
            return;
        }

        if (ping.getRequest().equals("Ping")) {
            PongPacket packet = PongPacket.newBuilder()
                    .setResponce("Pong N")
                    .build();
            System.out.println("Sending Pong");
            request.sendResponse(packet.toByteString(), Packet.FullPacket.PacketType.Pong);
        } else {
            ErrorPacket error = ErrorPacket.newBuilder()
                    .setCode(406)
                    .setDescr("Bad request, expected 'Ping'!")
                    .build();
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
        }
    }
}
