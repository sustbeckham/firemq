package com.mozilla.fire.consumer;

import com.mozilla.fire.consumer.netty.ConsumerClient;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
 * Created by mozilla on 2017/12/11.
 */
public class MessageConsumer {

    private Listener listener;

    private Channel channel;

    public MessageConsumer(){

        // 消息消费者建立和Broker之间的长连接
        channel = ConsumerClient.connectBroker();
    }

    /**
     * 设置消息监听器
     *
     * @param listener
     */
    public void addListener(Listener listener){
       this.listener = listener;
    }

    /**
     * 消息服务端启动
     */
    public void start(){
        while(true) {
           // Object message = brokerResult.getMessage();
           // listener.process(message);
        }
    }

    /**
     * 构建pull模式的请求
     *
     * @return
     */
    private ByteBuf buildPullRequest(){
        ByteBuf pull = Unpooled.buffer(16);
        pull.writeBytes("PULL".getBytes());
        return pull;
    }
}
