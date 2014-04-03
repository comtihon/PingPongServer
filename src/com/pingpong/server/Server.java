package com.pingpong.server;

import com.pingpong.core.Logger;
import com.pingpong.packet.gen.Packet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * Created by tihon on 03.04.14.
 */
public class Server {

    private final int port;

    public Server(int _port) {
        port = _port;
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new EpollEventLoopGroup();
        EventLoopGroup workerGroup = new EpollEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(EpollServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            // Decoders
                            ch.pipeline().addLast("frameDecoder",
                                    new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
                            ch.pipeline().addLast("protobufDecoder",
                                    new ProtobufDecoder(Packet.FullPacket.getDefaultInstance()));

                            // Encoder
                            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(4));
                            ch.pipeline().addLast("protobufEncoder", new ProtobufEncoder());
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

        Config conf = Config.getInstance();
        conf.initServerConf();
        conf.initLoggerConf();

        int port = Integer.valueOf(conf.getProperty("port", "4232"));
        try {
            Logger.i(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            Logger.i("Starting server on port %s", port);
            Logger.i(":::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            new Server(port).run();
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }
}
