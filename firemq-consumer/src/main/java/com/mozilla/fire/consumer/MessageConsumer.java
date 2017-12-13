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

        // ��Ϣ�����߽�����Broker֮��ĳ�����
        channel = ConsumerClient.connectBroker();
    }

    /**
     * ������Ϣ������
     *
     * @param listener
     */
    public void addListener(Listener listener){
       this.listener = listener;
    }

    /**
     * ��Ϣ���������
     */
    public void start(){
        while(true) {
           // Object message = brokerResult.getMessage();
           // listener.process(message);
        }
    }

    /**
     * ����pullģʽ������
     *
     * @return
     */
    private ByteBuf buildPullRequest(){
        ByteBuf pull = Unpooled.buffer(16);
        pull.writeBytes("PULL".getBytes());
        return pull;
    }
}
