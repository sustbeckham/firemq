package com.mozilla.fire;

import com.mozilla.fire.common.message.StringMessage;
import com.mozilla.fire.producer.MessageProducer;
import com.mozilla.fire.producer.SendResult;
import com.mozilla.fire.producer.impl.DefaultMessageProducer;

/**
 * Created by mozilla on 2017/12/11.
 */
public class ProducerTest {

    public static void main(String[] args) {
        StringMessage message = new StringMessage();
        message.setContent("Hello,FireMQ!");

        MessageProducer messageProducer = new DefaultMessageProducer();
        SendResult sendResult = messageProducer.sendMessage(message);

        System.out.println("消息发送结果:" + sendResult.isSuccess());
        System.out.println("消息发送时间:" + sendResult.getSendSuccessTime().toLocaleString());

        while(true){
            System.out.println("查看是否是deamon线程搞事情...");
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
