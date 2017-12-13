package com.mozilla.fire.common.http;

/**
 * 将MQ中所有网络交互请求抽象.
 *
 * Created by mozilla on 2017/12/12.
 */
public class Request<T> {

    // 本次请求唯一全局ID
    private long requestID;
    // 请求类型 @see com.mozilla.fire.common.constants.RequestType
    private int requestType;
    // 请求数据内容
    private T message;

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
