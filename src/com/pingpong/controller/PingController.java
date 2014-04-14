package com.pingpong.controller;

import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import com.pingpong.packet.gen.Packet;
import com.pingpong.server.ProcessorContainer;

import static com.pingpong.packet.gen.Error.ErrorPacket;
import static com.pingpong.packet.gen.Ping.PingPacket;
import static com.pingpong.packet.gen.Pong.PongPacket;

/**
 * Created by tihon on 04.04.14.
 */
public class PingController {
    /**
     * Processes the request. If body is ping - requests pong N, where N is
     * number of previous ping-requests.
     *
     * @param request Ping request to server
     */
    public static void processPing(final Request request) {
        PingPacket ping = request.getPing();
        if (ping == null) {
            ErrorPacket error = ErrorPacket.newBuilder()
                    .setCode(400)
                    .setDescr("Bad packet's body!")
                    .build();
            Logger.w("Can't parse packet's body!");
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
            return;
        }

        if (ping.getRequest().equals("Ping")) {
            String uid = ping.getUid();
            if (uid == null)
                uid = "";
            else
                ProcessorContainer.getLoaderProcessor().registerPinger(uid);

            long value = uid.isEmpty() ? 0 : ProcessorContainer.getStorateProcessor().getAndIncr(uid);

            //form and send response
            PongPacket packet = PongPacket.newBuilder()
                    .setResponce("Pong " + value)
                    .build();
            Logger.i("Sending " + packet.getResponce() + " to " + uid);
            request.sendResponse(packet.toByteString(), Packet.FullPacket.PacketType.Pong);
        } else {
            //form and send error
            ErrorPacket error = ErrorPacket.newBuilder()
                    .setCode(406)
                    .setDescr("Bad request, expected 'Ping'!")
                    .build();
            Logger.w("Got " + ping.getRequest() + " instead of 'Ping'");
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
        }
    }
}
