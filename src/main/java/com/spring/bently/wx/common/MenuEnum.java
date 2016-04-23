package com.spring.bently.wx.common;

/**
 * Created by wgq on 16-4-9.
 */
public enum MenuEnum {

    club_profile("club_profile","俱乐部简介"),
    club_dy("club_dy", "俱乐部动态"),
    user_activity("","会员活动回顾"),
    hotel_destine("hotel_destine","酒店预订"),
    ykt_destine("ykt_destine","游艇预订"),
    activity_destine("activity_destine","私人活动定制"),
    wash_car("wash_car","上门洗车"),
    waxing("waxing","上门打蜡"),
    maintence("maintence","预约保养"),
    rescue("rescue","市内救援"),
    secretary("secretary","服务秘书");

    private MenuEnum(String value, String content) {
        this.value = value ;
        this.content = content ;
    }

    private String value ;

    private String content ;

    public String getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

}
