package com.mozilla.fire.consumer;

import com.mozilla.fire.common.constants.MessageProcess;

/**
 * Created by mozilla on 2017/12/11.
 */
public interface Listener {

    /**
     * ������Ϣ
     *
     * @param message
     * @return
     */
    MessageProcess process(Object message);
}
