package com.mozilla.fire.broker;

/**
 *
 * Created by mozilla on 2017/12/11.
 */
public interface Broker {

    /**
     * ��ϢͶ��, ��Ϣ������ʹ��, producer
     *
     * @param message
     */
    BrokerResult deliver(Object message);

    /**
     * ��Ϣ��ȡ, ��Ϣ������ʹ��, consumer
     *
     * @return
     */
    BrokerResult pull();
}
