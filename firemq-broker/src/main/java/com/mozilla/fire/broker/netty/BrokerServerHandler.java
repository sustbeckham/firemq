package com.mozilla.fire.broker.netty;

import com.mozilla.fire.broker.Broker;
import com.mozilla.fire.common.config.BeanConfig;
import com.mozilla.fire.common.http.Request;
import com.mozilla.fire.common.http.Response;
import com.mozilla.fire.common.log.TLog;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BrokerServerHandler extends ChannelInboundHandlerAdapter {

    private Broker broker = BeanConfig.getInstance(Broker.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        Request request = (Request)msg;

        TLog.info("message received, message=" + request);

        // Netty服务端收到消息后, 将消息转发至核心broker层
        broker.deliver(request);

        Response response = new Response(request.getRequestID());
        response.setSuccess(true);
        response.setResponse("SUCCESS");
        ctx.write(response);
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
