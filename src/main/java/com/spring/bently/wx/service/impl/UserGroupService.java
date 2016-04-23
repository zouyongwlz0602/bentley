package com.spring.bently.wx.service.impl;

import com.spring.bently.wx.service.IUserGroupService;
import com.spring.bently.wx.utils.StringUtils;
import com.spring.bently.wx.utils.WeixiProperty;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;

import java.util.Map;

/**
 * Created by wgq on 16-4-16.
 */
public class UserGroupService implements IUserGroupService {
    @Override
    public String addGroup(Map<String, Object> map) {
        return null;
    }

    @Override
    public String listGroup() {
        //{"groups":[{"id":0,"name":"未分组","count":10},{"id":1,"name":"黑名单","count":0},{"id":2,"name":"星标组","count":0},{"id":100,"name":"会员","count":0}]}
        String postUrl = StringUtils.replaceEach(WeixinPropertiesUtils.getProperties("group_list_url"), WeixiProperty.ACCESSTOKEN) ;
        CustomHttpsConnection connection = new CustomHttpsConnection(new HttpConnectionCommon(postUrl,"GET")) ;
        return connection.httpsClient(null);
    }

    @Override
    public String getGroupByOpenid(Map<String, Object> map) {
        return null;
    }
}
