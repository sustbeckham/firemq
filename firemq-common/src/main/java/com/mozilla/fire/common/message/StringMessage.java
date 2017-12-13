package com.mozilla.fire.common.message;

import java.io.Serializable;

/**
 * Created by mozilla on 2017/12/12.
 */
public class StringMessage extends BaseMessage implements Serializable{

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
