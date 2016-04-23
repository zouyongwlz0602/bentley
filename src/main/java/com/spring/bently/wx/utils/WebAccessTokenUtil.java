package com.spring.bently.wx.utils;

import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by wgq on 16-4-10.
 */
public class WebAccessTokenUtil {

    private static Logger log = LoggerFactory.getLogger(WebAccessTokenUtil.class) ;

    public static Map access_token_request(String code) {
        String code_access_token_url = WeixinPropertiesUtils.getProperties("code_access_token_url") ;
        String URL = StringUtils.replaceEach(code_access_token_url,WeixinPropertiesUtils.getProperties("appid"),
                WeixinPropertiesUtils.getProperties("secret"),code) ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(URL,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String accessTokenJson = connection.httpsClient(null) ;
        log.info("accessTokenJson = " + accessTokenJson);

        Map accessTokenmap = JsonUtils.jsonToMap(accessTokenJson) ;
        if(accessTokenmap == null || accessTokenmap.get("errcode") != null) {

            return null ;
        }
        String access_token = accessTokenmap.get("access_token").toString() ;
        String openid = accessTokenmap.get("openid").toString() ;

        Map userinfomap = userinfo_request(access_token,openid) ;
        userinfomap.put("access_token",access_token) ;
        userinfomap.put("refresh_token",accessTokenmap.get("refresh_token")) ;
        return userinfomap ;

    }

    public static Map week_access_token_request(String code) {
        String code_access_token_url = WeixinPropertiesUtils.getProperties("code_access_token_url") ;
        String URL = StringUtils.replaceEach(code_access_token_url,WeixinPropertiesUtils.getProperties("appid"),
                WeixinPropertiesUtils.getProperties("secret"),code) ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(URL,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String accessTokenJson = connection.httpsClient(null) ;
        log.info("accessTokenJson = " + accessTokenJson);

        Map accessTokenmap = JsonUtils.jsonToMap(accessTokenJson) ;
        if(accessTokenmap == null || accessTokenmap.get("errcode") != null) {

            return null ;
        }
        String openid = accessTokenmap.get("openid").toString() ;
        Map userinfomap = userinfo_request_normal(WeixiProperty.ACCESSTOKEN,openid) ;
        return userinfomap ;
    }

    //通过web_access_token和openid获取用户信息
    private static Map userinfo_request(String access_token,String openid) {
        String get_userinfo_url = WeixinPropertiesUtils.getProperties("get_userinfo_url") ;
        String URL = StringUtils.replaceEach(get_userinfo_url,access_token,openid) ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(URL,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String userinfoJson = connection.httpsClient(null) ;
        Map userinfomap = JsonUtils.jsonToMap(userinfoJson) ;
        if(userinfomap == null || userinfomap.get("errcode") != null) {

            return null ;
        }
        userinfomap.put("access_token",access_token) ;

        return userinfomap ;
    }

    //通过access_token和openid获取用户信息
    public static Map userinfo_request_normal(String access_token,String openid) {
        String get_userinfo_url = WeixinPropertiesUtils.getProperties("get_normal_userinfo_url") ;
        String URL = StringUtils.replaceEach(get_userinfo_url,access_token,openid) ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(URL,"GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String userinfoJson = connection.httpsClient(null) ;
        Map userinfomap = JsonUtils.jsonToMap(userinfoJson) ;
        if(userinfomap == null || userinfomap.get("errcode") != null) {

            return null ;
        }
        userinfomap.put("access_token",access_token) ;

        return userinfomap ;
    }
}
