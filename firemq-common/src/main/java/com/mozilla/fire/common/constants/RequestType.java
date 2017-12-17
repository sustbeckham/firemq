package com.mozilla.fire.common.constants;

/**
 * Created by mozilla on 2017/12/12.
 */
public enum RequestType {

    DELIVER(1, "消息投递"),
    HB(2, "心跳"),
    PULL_REQUEST(3, "消息pull请求");

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
