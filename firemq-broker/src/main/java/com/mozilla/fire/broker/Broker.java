package com.mozilla.fire.broker;

/**
 *
 * Created by mozilla on 2017/12/11.
 */
public interface Broker {

    /**
     * 消息投递, 消息生产者使用, producer
     *
     * @param message
     */
    BrokerResult deliver(Object message);

    /**
     * 消息获取, 消息消费者使用, consumer
     *
     * @return
     */
    BrokerResult pull();
}
