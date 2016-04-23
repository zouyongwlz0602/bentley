package com.spring.bently.wx.common;

/**
 * Created by wgq on 16-4-10.
 */
public enum MsgTypeEnum {
    text("text"),
    image("image"),
    voice("voice"),
    video("video"),
    shortvideo("shortvideo"),
    location("location"),
    link("link"),
    event("event");

    private String value ;

    private MsgTypeEnum(String value) {

        this.value = value ;
    }

    public String getValue() {
        return value;
    }

}
