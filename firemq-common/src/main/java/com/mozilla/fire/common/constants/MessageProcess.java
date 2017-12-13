package com.mozilla.fire.common.constants;

/**
 * 业务消息处理结果
 *
 * Created by mozilla on 2017/12/11.
 */
public enum MessageProcess {

    SUCCESS(1, "成功"),
    RETRY(2, "消息需要稍后重投"),
    FAIL(3, "消费端处理失败");

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
