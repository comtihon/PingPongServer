package com.pingpong.model;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.pingpong.core.Logger;
import com.pingpong.manager.PacketManager;
import com.pingpong.packet.gen.Error;
import com.pingpong.packet.gen.Packet;
import com.pingpong.packet.gen.Ping;
import com.pingpong.server.ProcessorContainer;
import com.pingpong.server.Server;
import io.netty.channel.ChannelHandlerContext;

import static com.pingpong.packet.gen.Packet.FullPacket.PacketType;

/**
 * Created by tihon on 04.04.14.
 */
public class Request implements Runnable {

    private PacketType type;
    private int protocol;
    private int api;
    private ByteString rawBody;
    private ChannelHandlerContext channel;

    public Request(Packet.FullPacket msg, ChannelHandlerContext ctx) {
        type = msg.getType();
        protocol = msg.getProtocol();
        api = msg.getApiVersion();
        rawBody = msg.getPacket();
        channel = ctx;
    }

    @Override
    public void run() {
        try {
            PacketManager.processPacket(this);
        } catch (Exception e) {
            Logger.w("Dropped packet [%s]", toString());
            Logger.e(e.getMessage());
            com.pingpong.packet.gen.Error.ErrorPacket error = Error.ErrorPacket.newBuilder()
                    .setCode(500)
                    .setDescr("Can't process packet!")
                    .build();
            sendResponse(error.toByteString(), PacketType.Error);
        }

        ProcessorContainer.getRequestsProcessor().removeRequest(this);
    }

    public void sendResponse(ByteString message, PacketType type) {
        if (channel.channel().isOpen()) {
            Server server = Server.getInstance();

            Packet.FullPacket packet = Packet.FullPacket.newBuilder()
                    .setType(type)
                    .setProtocol(server.getCurrentProtocolVersion())
                    .setApiVersion(server.getMinApiVersion())
                    .setPacket(message)
                    .build();
            channel.write(packet);
            channel.flush();
        }
        if(channel.channel().isOpen())
            channel.close();
    }

    public Ping.PingPacket getPing() {
        try {
            return Ping.PingPacket.parseFrom(rawBody);
        } catch (InvalidProtocolBufferException e) {
            Logger.w("Can't parse message body! : " + e.getMessage());
            return null;
        }
    }

    public PacketType getType() {
        return type;
    }
}
