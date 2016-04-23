package com.spring.bently.wx.service.impl;

import com.spring.bently.wx.service.MenuService;
import com.spring.bently.wx.utils.StringUtils;
import com.spring.bently.wx.utils.WeixiProperty;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by wgq on 16-4-4.
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static Logger log = LoggerFactory.getLogger(MenuServiceImpl.class) ;

    public String createMenu(String menu) {

        String create_menu_url = WeixinPropertiesUtils.getProperties("create_menu_url") ;
        String access_token = WeixiProperty.ACCESSTOKEN ;

        String postUrl = StringUtils.replaceEach(create_menu_url,access_token) ;
       // String postUrl = WeixinPropertiesUtils.getProperties("create_menu_url") + "?"
        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl, "POST") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String message = connection.httpsClient(menu) ;
        log.info(postUrl);
        return message ;
    }

    @Override
    public String deleteMenu() {
        String delete_menu_url = WeixinPropertiesUtils.getProperties("delete_menu_url") ;
        String access_token = WeixiProperty.ACCESSTOKEN ;

        String postUrl = StringUtils.replaceEach(delete_menu_url,access_token) ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        log.info(postUrl);
        return connection.httpsClient(null) ;
    }

    public String selectMenu() {

        String delete_menu_url = WeixinPropertiesUtils.getProperties("select_menu_url") ;
        String access_token = WeixiProperty.ACCESSTOKEN ;

        String postUrl = StringUtils.replaceEach(delete_menu_url,access_token) ;
        log.info(postUrl);
        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        return connection.httpsClient(null) ;
    }
}
