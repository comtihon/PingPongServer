package com.pingpong.controller;

import com.pingpong.manager.CacheManager;
import com.pingpong.core.Logger;
import com.pingpong.manager.DatabaseManager;
import com.pingpong.model.Request;
import com.pingpong.packet.gen.Packet;

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
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
            return;
        }

        if (ping.getRequest().equals("Ping")) {
            String uid = ping.getUid();
            if (uid == null)
                uid = "";
            //form and send response
            PongPacket packet = PongPacket.newBuilder()
                    .setResponce("Pong " + getPingValue(uid))
                    .build();
            Logger.i("Sending " + packet.getResponce() + " to " + uid);
            request.sendResponse(packet.toByteString(), Packet.FullPacket.PacketType.Pong);
        } else {
            //form and send error
            ErrorPacket error = ErrorPacket.newBuilder()
                    .setCode(406)
                    .setDescr("Bad request, expected 'Ping'!")
                    .build();
            request.sendResponse(error.toByteString(), Packet.FullPacket.PacketType.Error);
        }
    }

    /**
     * Remembers pinger in list for synchronisation with database and try to get old value from cache or database
     *
     * @param uid pinger's uid
     * @return number of pings - made previosely, min 1 (current ping).
     */
    private static long getPingValue(String uid) {
        DatabaseManager.getInstance().addPinger(uid);   //add connected pinger to synchronisation array
        long result = CacheManager.incr(uid);  //get result from cache
        Logger.d("Result is " + result);
        if (result == 1) {  //cache record is new. Search database for old ping values
            long pingsFromDb = DatabaseManager.getInstance().getPingValue(uid);
            Logger.d("pings from db  " + pingsFromDb);
            if (pingsFromDb > 0) {
                CacheManager.incr(uid, pingsFromDb + 1);    //updates cache with database info
                result += pingsFromDb;
            }
        }
        return result;
    }
}
