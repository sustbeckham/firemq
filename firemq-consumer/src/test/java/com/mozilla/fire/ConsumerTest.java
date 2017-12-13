package com.mozilla.fire;

import com.mozilla.fire.common.constants.MessageProcess;
import com.mozilla.fire.consumer.Listener;
import com.mozilla.fire.consumer.MessageConsumer;

/**
 * 消费端的测试
 *
 * Created by mozilla on 2017/12/11.
 */
public class ConsumerTest {

    public static void main(String[] args) {

         System.out.println("消费端现在开启。");

         MessageConsumer consumer = new MessageConsumer();
         consumer.addListener(new Listener() {
             @Override
             public MessageProcess process(Object message) {
                 System.out.println("消息处理成功, message=" + message);
                 return MessageProcess.SUCCESS;
             }
         });
         consumer.start();
    }
}
