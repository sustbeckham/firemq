package com.mozilla.fire.common.http;

/**
 * 将MQ中所有网络交互返回结果抽象.
 *
 * Created by mozilla on 2017/12/12.
 */
public class Response<T> {

    // 本次请求的ID。和对应request关联。
    private long requestID;
    // 本次请求结果状态是否为成功
    private boolean success;
    // 错误码。当且仅当success为false时有效。
    private int errorCode;
    // 返回的数据结果
    private T response;

    public Response(long requestID){
        this.requestID = requestID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
