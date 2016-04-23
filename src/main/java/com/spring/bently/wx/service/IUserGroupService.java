package com.spring.bently.wx.service;

import java.util.Map;

/**
 * Created by wgq on 16-4-16.
 */
public interface IUserGroupService {

    public String addGroup(Map<String,Object> map) ;

    public String listGroup() ;

    public String getGroupByOpenid(Map<String,Object> map) ;
}
