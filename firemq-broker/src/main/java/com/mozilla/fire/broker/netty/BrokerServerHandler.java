package com.mozilla.fire.broker.netty;

import com.mozilla.fire.broker.Broker;
import com.mozilla.fire.broker.BrokerResult;
import com.mozilla.fire.common.config.BeanConfig;
import com.mozilla.fire.common.constants.RequestType;
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
        TLog.info("[Broker] message received, message=" + request);

        // 这里开始需要处理多钟不同的request类型。
        int requestType = request.getRequestType();
        if(requestType == RequestType.DELIVER.getCode()){
            handleRequestDeliver(ctx, request);
        }else if(requestType == RequestType.PULL_REQUEST.getCode()){
            handlePullRequest(ctx, request);
        }

    }

    /**
     * 处理消息发送者丢来的数据
     *
     * @param ctx
     * @param request
     */
    private void handleRequestDeliver(ChannelHandlerContext ctx, Request request){
        // Netty服务端收到消息后, 将消息转发至核心broker层
        broker.deliver(request);

        Response response = new Response(request.getRequestID());
        response.setSuccess(true);
        response.setResponse("SUCCESS");
        ctx.write(response);
    }

    /**
     * 处理消息消费者的pull模式请求数据
     *
     * @param ctx
     * @param request
     */
    private void handlePullRequest(ChannelHandlerContext ctx, Request request){

        BrokerResult brokerResult = broker.pull();

        Response response = new Response(request.getRequestID());
        response.setSuccess(true);
        response.setResponse(brokerResult.getMessage());
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TLog.warn("[Broker] a channel active:" + ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TLog.warn("[Broker] a channel inactive:" + ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        TLog.warn("[Broker] an exception is raised:" + ctx.channel(), cause);
        ctx.close();
    }
}
