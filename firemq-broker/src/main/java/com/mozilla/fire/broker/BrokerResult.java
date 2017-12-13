package com.mozilla.fire.broker;

/**
 * 消息投递返回的结果内容
 *
 * Created by mozilla on 2017/12/11.
 */
public class BrokerResult {

    private Object message;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
