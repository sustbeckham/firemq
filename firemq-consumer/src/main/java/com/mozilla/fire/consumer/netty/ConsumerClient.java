package com.mozilla.fire.consumer.netty;

import com.mozilla.fire.common.NamedThreadFactory;
import com.mozilla.fire.common.http.decoder.ResponseDecoder;
import com.mozilla.fire.common.http.encoder.RequestEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by mozilla on 2017/12/12.
 */
public class ConsumerClient {

    private static final String WORKER = "Worker";

    private static final int PORT = 12307;

    public static final NioEventLoopGroup workerGroup = new NioEventLoopGroup(
            Runtime.getRuntime().availableProcessors() * 2,
            new NamedThreadFactory(WORKER));

    public static final ByteBufAllocator byteBufAllocator = UnpooledByteBufAllocator.DEFAULT;

    public static Channel connectBroker() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.ALLOCATOR, byteBufAllocator)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast("decoder", new ResponseDecoder())
                                .addLast("encoder", new RequestEncoder()).addLast(new ConsumerHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", PORT));
        if (future.awaitUninterruptibly(1000) && future.isSuccess() && future.channel().isActive()) {
            return future.channel();
        } else {
            future.cancel(true);
            future.channel().close();
            throw new RuntimeException("connect broker error, port:" + PORT);
        }
    }
}
