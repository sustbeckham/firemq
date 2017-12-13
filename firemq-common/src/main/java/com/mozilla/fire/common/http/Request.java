package com.mozilla.fire.common.http;

/**
 * ��MQ���������罻���������.
 *
 * Created by mozilla on 2017/12/12.
 */
public class Request<T> {

    // ��������Ψһȫ��ID
    private long requestID;
    // �������� @see com.mozilla.fire.common.constants.RequestType
    private int requestType;
    // ������������
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
