package com.mozilla.fire.consumer.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mozilla on 2017/12/12.
 */
public class ConsumerHandler extends ChannelInboundHandlerAdapter {

//    private final ByteBuf firstMessage;
//
//    public ConsumerHandler() {
//        firstMessage = Unpooled.buffer(1024);
//        for (int i = 0; i < firstMessage.capacity(); i ++) {
//            firstMessage.writeByte((byte) i);
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(firstMessage);
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
