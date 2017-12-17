package com.mozilla.fire;

import com.mozilla.fire.common.constants.MessageProcess;
import com.mozilla.fire.common.message.BaseMessage;
import com.mozilla.fire.common.message.StringMessage;
import com.mozilla.fire.consumer.Listener;
import com.mozilla.fire.consumer.MessageConsumer;
import com.sun.xml.internal.rngom.parse.host.Base;

/**
 * 消费端的测试
 *
 * Created by mozilla on 2017/12/11.
 */
public class ConsumerTest {

    public static void main(String[] args) {
         MessageConsumer consumer = new MessageConsumer();
         consumer.addListener(new Listener() {
             @Override
             public MessageProcess process(BaseMessage message) {

                 if(message instanceof StringMessage){
                     System.out.println("消息处理成功, message=" + ((StringMessage)message).getContent());
                 }else{
                     System.out.println("不支持的消息类型。 message=" + message);
                 }

                 return MessageProcess.SUCCESS;
             }
         });
         consumer.start();
    }
}
