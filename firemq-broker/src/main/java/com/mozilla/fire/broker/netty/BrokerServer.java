package com.mozilla.fire.broker.netty;

import com.mozilla.fire.common.NamedThreadFactory;
import com.mozilla.fire.common.http.decoder.RequestDecoder;
import com.mozilla.fire.common.http.encoder.ResponseEncoder;
import com.mozilla.fire.common.log.TLog;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class BrokerServer {

    private final String BOSS = "Boss";

    private final String WORKER = "Worker";

    private final int PORT = 12307;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(
            0,
            new NamedThreadFactory(WORKER));

    public final NioEventLoopGroup workerGroup = new NioEventLoopGroup(
            Runtime.getRuntime().availableProcessors() * 2,
            new NamedThreadFactory(BOSS));


    public static final ByteBufAllocator byteBufAllocator = UnpooledByteBufAllocator.DEFAULT;

    /**
     * 服务提供端启动
     *
     * @throws Exception
     */
    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, byteBufAllocator)
                .childOption(ChannelOption.ALLOCATOR, byteBufAllocator)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("decoder", new RequestDecoder())
                                .addLast("encoder", new ResponseEncoder())
                                .addLast("handler", new BrokerServerHandler());
                    }
                });

        long tryBind = 3;
        while (tryBind > 0) {
            ChannelFuture cf = bootstrap.bind(new InetSocketAddress("localhost", PORT));
            cf.await();
            if (cf.isSuccess()) {
                TLog.warn("broker server start success, port: " + PORT);

                cf.channel().closeFuture().sync();
                return;
            } else {
                tryBind--;
                if (tryBind <= 0) {
                    TLog.warn("broker server start retry 3 times error, port:" + PORT);
                    System.exit(1);
                } else {
                    TLog.warn("broker server start error, after 3 second will retry. port:" + PORT, cf.cause());
                    Thread.sleep(3000);
                }
            }
        }
    }
}
