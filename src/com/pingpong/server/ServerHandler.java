package com.pingpong.server;

import com.pingpong.core.Logger;
import com.pingpong.model.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static com.pingpong.packet.gen.Error.ErrorPacket;
import static com.pingpong.packet.gen.Packet.FullPacket;

/**
 * Created by tihon on 03.04.14.
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullPacket> {

    private final int minProtocolVersion;
    private final int minApiVersion;
    private final int currentProtocolVersion;

    public ServerHandler() {
        Server server = Server.getInstance();
        minApiVersion = server.getMinApiVersion();
        minProtocolVersion = server.getMinProtocolVersion();
        currentProtocolVersion = server.getCurrentProtocolVersion();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        sendError(400, "Unknown packet type or damaged packet!", ctx);
        ctx.flush();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullPacket msg) throws Exception {
        if (msg.getProtocol() < minProtocolVersion || msg.getApiVersion() < minApiVersion) {
            sendError(406, "Your protocol version is outdated. Please update your client", ctx);
            ctx.close();
            return;
        }
        Request request = new Request(msg, ctx);
        ProcessorContainer.getRequestsProcessor().addRequest(request);
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
