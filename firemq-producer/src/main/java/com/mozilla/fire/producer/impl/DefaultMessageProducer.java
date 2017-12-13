package com.mozilla.fire.producer.impl;

import com.mozilla.fire.broker.Broker;
import com.mozilla.fire.common.Utils;
import com.mozilla.fire.common.config.BeanConfig;
import com.mozilla.fire.common.constants.RequestType;
import com.mozilla.fire.common.http.Request;
import com.mozilla.fire.common.http.RequestFuture;
import com.mozilla.fire.common.http.Response;
import com.mozilla.fire.common.log.TLog;
import com.mozilla.fire.common.message.BaseMessage;
import com.mozilla.fire.producer.MessageProducer;
import com.mozilla.fire.producer.SendResult;
import com.mozilla.fire.producer.netty.ProducerClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Date;

/**
 * 默认的消息投递实现
 *
 * Created by mozilla on 2017/12/11.
 */
public class DefaultMessageProducer implements MessageProducer {

    private Broker broker;

    private Channel channel;

    public DefaultMessageProducer(){
        this.broker = BeanConfig.getInstance(Broker.class);

        // 消息生产者建立和Broker之间的长连接
        channel = ProducerClient.connectBroker();
    }


    public SendResult sendMessage(BaseMessage message) {
        SendResult sendResult = new SendResult();

        // 1. 将消息内容包装成抽象的Request对象
        Request request = new Request();
        request.setRequestID(Utils.nextID());
        request.setRequestType(RequestType.DELIVER.getCode());
        request.setMessage(message);

        // 2. write RequestFuture
        RequestFuture future = RequestFuture.create(request);
        channel.writeAndFlush(future);

        try {
            future.get();
        }catch (Exception e){
            TLog.error("sendMessage error", e);
        }

        sendResult.setSuccess(true);
        sendResult.setSendSuccessTime(new Date());
        return sendResult;
    }
}










