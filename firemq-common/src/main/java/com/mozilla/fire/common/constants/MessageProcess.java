package com.mozilla.fire.common.constants;

/**
 * ҵ����Ϣ������
 *
 * Created by mozilla on 2017/12/11.
 */
public enum MessageProcess {

    SUCCESS(1, "�ɹ�"),
    RETRY(2, "��Ϣ��Ҫ�Ժ���Ͷ"),
    FAIL(3, "���Ѷ˴���ʧ��");

    private int code;
    private String description;

    MessageProcess(int code, String description){

    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
