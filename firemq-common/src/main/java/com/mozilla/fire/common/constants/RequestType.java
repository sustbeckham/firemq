package com.mozilla.fire.common.constants;

/**
 * Created by mozilla on 2017/12/12.
 */
public enum RequestType {

    DELIVER(1, "��ϢͶ��"),
    HB(2, "����"),
    CONNECT_CLOSE(3, "�ر�����");

    private int code;
    private String description;

    RequestType(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
