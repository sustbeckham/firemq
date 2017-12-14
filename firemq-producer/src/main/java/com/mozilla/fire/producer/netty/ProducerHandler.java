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

    // ��¼��ǰrequestID��future�Ĺ�ϵ��
    // ע��:��ȷ���õ��������Ҫ�����Ӧkey�µ�ֵ������ô��������ڴ�й©��
    private ConcurrentHashMap<Long, RequestFuture> futureMap = new ConcurrentHashMap<Long, RequestFuture>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Response) {
            Response response = (Response) msg;
            RequestFuture future = futureMap.get(response.getRequestID());

            // ����д��future
            future.onResponse(response);

            // ����future��map��ϵ����, �����ڴ�����
            futureMap.remove(response.getRequestID());
        } else {
            TLog.warn("receive message error,only support Response");
        }
    }

    @Override
    public void write(final ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if(msg instanceof RequestFuture){
            RequestFuture future = (RequestFuture)msg;

            // �洢requestID��future�Ĺ�ϵ������response������Ҫ����future
            futureMap.put(future.getRequest().getRequestID(), future);

            // ���ݷ����������
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
//            // �����˶���ʱ����д��ʱ�¼�, �رյ�����˵�����
//            if (e.state() == IdleState.READER_IDLE || e.state() == IdleState.WRITER_IDLE
//                    || e.state() == IdleState.ALL_IDLE) {
//                Channel channel = ctx.channel();
//                logger.warn("��ʱ, ���ӹرա�remote=" + channel.remoteAddress());
//                ctx.close();
//            }
//        }
//    }
}
