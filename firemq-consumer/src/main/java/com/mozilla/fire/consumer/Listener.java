package com.mozilla.fire.consumer;

import com.mozilla.fire.common.constants.MessageProcess;
import com.mozilla.fire.common.message.BaseMessage;

/**
 * Created by mozilla on 2017/12/11.
 */
public interface Listener {

    /**
     * 处理消息
     *
     * @param message
     * @return
     */
    MessageProcess process(BaseMessage message);
}
