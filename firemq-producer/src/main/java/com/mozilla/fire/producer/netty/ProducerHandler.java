package com.mozilla.fire.producer.netty;

import com.mozilla.fire.common.http.RequestFuture;
import com.mozilla.fire.common.http.Response;
import com.mozilla.fire.common.log.TLog;
import io.netty.channel.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mozilla on 2017/12/12.
 */
public class ProducerHandler extends ChannelDuplexHandler {

    // 记录当前requestID和future的关系。
    // 注意:当确认拿到结果后，需要清理对应key下的值。否则该处会引起内存泄漏。
    private ConcurrentHashMap<Long, RequestFuture> futureMap = new ConcurrentHashMap<Long, RequestFuture>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Response) {
            Response response = (Response) msg;
            RequestFuture future = futureMap.get(response.getRequestID());

            // 数据写回future
            future.onResponse(response);

            // 清理future的map关系引用, 清理内存数据
            futureMap.remove(response.getRequestID());
        } else {
            TLog.warn("receive message error,only support Response");
        }
    }

    @Override
    public void write(final ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof RequestFuture){
            RequestFuture future = (RequestFuture)msg;

            // 存储requestID和future的关系，后续response返回需要唤醒future
            futureMap.put(future.getRequest().getRequestID(), future);

            // 数据发送至服务端
            ctx.writeAndFlush(future.getRequest(), promise);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof java.net.ConnectException) {
            return;
        }
        TLog.warn("catch some exception:" + ctx.channel(), cause);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TLog.warn("[provider]a channel inactive:" + ctx.channel());
    }
//
//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent e = (IdleStateEvent) evt;
//
//            // 触发了读超时或者写超时事件, 关闭到服务端的连接
//            if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.WRITER_IDLE
//                    || e.state() == IdleState.ALL_IDLE) {
//                Channel channel = ctx.channel();
//                logger.warn("超时, 连接关闭。remote=" + channel.remoteAddress());
//                ctx.close();
//            }
//        }
//    }
}
