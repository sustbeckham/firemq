package com.mozilla.fire.producer;

import java.util.Date;

/**
 * Created by mozilla on 2017/12/11.
 */
public class SendResult {

    // ��Ϣ�Ƿ��ͳɹ�
    private boolean success;
    // ��Ϣ���ͳɹ�ʱ��
    private Date sendSuccessTime;

    public Date getSendSuccessTime() {
        return sendSuccessTime;
    }

    public void setSendSuccessTime(Date sendSuccessTime) {
        this.sendSuccessTime = sendSuccessTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
