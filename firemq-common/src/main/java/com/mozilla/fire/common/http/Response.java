package com.mozilla.fire.common.http;

/**
 * ��MQ���������罻�����ؽ������.
 *
 * Created by mozilla on 2017/12/12.
 */
public class Response<T> {

    // ���������ID���Ͷ�Ӧrequest������
    private long requestID;
    // ����������״̬�Ƿ�Ϊ�ɹ�
    private boolean success;
    // �����롣���ҽ���successΪfalseʱ��Ч��
    private int errorCode;
    // ���ص����ݽ��
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
