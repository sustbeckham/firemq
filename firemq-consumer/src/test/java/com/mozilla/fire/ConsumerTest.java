package com.mozilla.fire;

import com.mozilla.fire.common.constants.MessageProcess;
import com.mozilla.fire.consumer.Listener;
import com.mozilla.fire.consumer.MessageConsumer;

/**
 * ���Ѷ˵Ĳ���
 *
 * Created by mozilla on 2017/12/11.
 */
public class ConsumerTest {

    public static void main(String[] args) {

         System.out.println("���Ѷ����ڿ�����");

         MessageConsumer consumer = new MessageConsumer();
         consumer.addListener(new Listener() {
             @Override
             public MessageProcess process(Object message) {
                 System.out.println("��Ϣ����ɹ�, message=" + message);
                 return MessageProcess.SUCCESS;
             }
         });
         consumer.start();
    }
}
