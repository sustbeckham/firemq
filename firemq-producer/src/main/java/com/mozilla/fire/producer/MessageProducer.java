package com.mozilla.fire.producer;

import com.mozilla.fire.common.message.BaseMessage;

/**
 * Created by mozilla on 2017/12/11.
 */
public interface MessageProducer {

    /**
     * ÏûÏ¢Í¶µİ
     *
     * @param message
     * @return
     */
    SendResult sendMessage(BaseMessage message);
}
