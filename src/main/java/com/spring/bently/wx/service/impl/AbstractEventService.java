package com.spring.bently.wx.service.impl;

import com.spring.bently.wx.common.EventEnum;
import com.spring.bently.wx.service.IEventService;

import java.util.Map;

/**
 * Created by wgq on 16-4-10.
 */

public abstract class AbstractEventService implements IEventService {
    @Override
    public String event(Map<String, String> map) {
        String event = map.get("event") ;
        String msg = "" ;
        switch (EventEnum.valueOf(event)) {
            case subscribe:
                return subscribeEvent(map) ;
            case unsubscribe:
                return unsubscribeEvent(map) ;
            case SCAN:
                return scanEvent(map) ;
            case LOCATION:
                return locationEvent(map) ;
            case CLICK:

                return clickEvent(map) ;
            case VIEW:
                return viewEvent(map) ;
            default:
                return "success" ;
        }
    }
    //点击菜单事件
    public abstract String clickEvent(Map<String, String> map) ;

    //关注事件
    public abstract String subscribeEvent(Map<String, String> map) ;

    //取消关注事件
    public abstract String unsubscribeEvent(Map<String, String> map) ;

    //已关注扫描二维码事件
    public abstract String scanEvent(Map<String, String> map) ;

    //上报地理位置事件
    public abstract String locationEvent(Map<String, String> map) ;

    //点击链接跳转事件
    public abstract String viewEvent(Map<String, String> map) ;
}
