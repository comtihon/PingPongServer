package com.pingpong.server;

import com.pingpong.core.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.pingpong.packet.gen.Error.ErrorPacket;
import static com.pingpong.packet.gen.Packet.FullPacket;

/**
 * Created by tihon on 03.04.14.
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullPacket> {

    private final int minApiVersion;
    private final int minProtocolVersion;
    private final int currentProtocolVersion;

    public ServerHandler() {
        minApiVersion = Integer.valueOf(Config.getInstance().getProperty("min_support_api", "1"));
        currentProtocolVersion = Integer.valueOf(Config.getInstance().getProperty("current_protocol_version", "1"));
        minProtocolVersion = Integer.valueOf(Config.getInstance().getProperty("minimal_protocol_version", "1"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        sendError(400, "Unknown packet type or damaged packet!", ctx);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullPacket msg) throws Exception {
        if (msg.getProtocol() < minProtocolVersion || msg.getApiVersion() < minApiVersion) {
            sendError(406, "Your protocol version is outdated. Please update your client", ctx);
            ctx.close();
            return;
        }
        System.out.println("Got " + msg.getApiVersion());
    }

    private void sendError(int code, String message, ChannelHandlerContext channel) {
        Logger.w(message);
        ErrorPacket error = ErrorPacket.newBuilder()
                .setCode(code)
                .setDescr(message)
                .build();

        FullPacket packet = FullPacket.newBuilder()
                .setType(FullPacket.PacketType.Error)
                .setProtocol(minApiVersion)
                .setApiVersion(currentProtocolVersion)
                .setPacket(error.toByteString())
                .build();
        channel.write(packet);
    }
}
