package com.mozilla.fire.broker;

/**
 * Created by mozilla on 2017/12/11.
 */
public interface Store {

    /**
     * 消息持久化
     *
     * @param message
     * @return
     */
    StoreResult write(Object message);

    /**
     * 消息内容获取
     *
     * @return
     */
    Object consume();
}
