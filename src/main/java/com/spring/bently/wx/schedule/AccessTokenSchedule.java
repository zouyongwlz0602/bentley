package com.spring.bently.wx.schedule;

import com.spring.bently.manager.dao.AccessTokenDao;
import com.spring.bently.manager.model.AccessToken;
import com.spring.bently.wx.utils.JsonUtils;
import com.spring.bently.wx.utils.StringUtils;
import com.spring.bently.wx.utils.WeixiProperty;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by wgq on 16-4-4.
 */
@Component
public class AccessTokenSchedule {

    private static Logger log = LoggerFactory.getLogger(AccessTokenSchedule.class) ;

    @Autowired
    private AccessTokenDao accessTokenDao ;

    //每个小时触发一次
    @Scheduled(cron = "0 0 * * * ?")
    public void getAccessTokenScheduled() {
        log.info("进入access_token........");

        String access_token_url = WeixinPropertiesUtils.getProperties("access_token_url") ;
        String appid = WeixinPropertiesUtils.getProperties("appid") ;
        String secret = WeixinPropertiesUtils.getProperties("secret") ;

        String postUrl = StringUtils.replaceEach(access_token_url,appid,secret) ;
        log.info("postUrl = " + postUrl);

        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl, "GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String jsonResult = connection.httpsClient(null) ;
        log.info("jsonResult = " + jsonResult);
        Map map = JsonUtils.jsonToMap(jsonResult);
        if(map == null) {
            log.info("获取access_token的json格式字符串有问题，无法转成map对象");
            return ;
        }

        String errcode = map.get("errcode")==null?"":map.get("errcode").toString() ;
        if(StringUtils.isEmpty(errcode)) {

            AccessToken accessToken = accessTokenDao.findByType("normal") ;
            if(accessToken == null) {
                accessToken = new AccessToken() ;
            }
            WeixiProperty.ACCESSTOKEN = map.get("access_token").toString() ;
            accessToken.setAccesstoken(map.get("access_token").toString());
            //accessToken.setType("normal");
            accessTokenDao.save(accessToken) ;
            //WeixinPropertiesUtils.setProperties("access_token",map.get("access_token").toString());
        }

        log.info("WeixiProperty.ACCESSTOKEN = " + WeixiProperty.ACCESSTOKEN);

    }

    @Scheduled(cron = "24 30 * * * ?")
    public void jsapiTicketScheduled() {
        log.info("进入jsapi_ticket........");
        String jsapi_ticket_url = WeixinPropertiesUtils.getProperties("jsapi_ticket_url") ;
        String access_token = WeixiProperty.ACCESSTOKEN ;

        String postUrl = StringUtils.replaceEach(jsapi_ticket_url,access_token) ;
        log.info("postUrl = " + postUrl);

        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl, "GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String jsonResult = connection.httpsClient(null) ;
        log.info("jsonResult = " + jsonResult);
        Map map = JsonUtils.jsonToMap(jsonResult);
        if(map == null) {
            log.info("获取jsapi_ticket的json格式字符串有问题，无法转成map对象");
            return ;
        }
        String errcode = map.get("errcode")==null?"":map.get("errcode").toString() ;
        if("0".equals(errcode)) {

            AccessToken jsapiticket = accessTokenDao.findByType("jsapi_ticket") ;
            if(jsapiticket == null) {
                jsapiticket = new AccessToken() ;
            }
            WeixiProperty.JSAPITICKET = map.get("ticket").toString() ;
            jsapiticket.setAccesstoken(map.get("ticket").toString());
            //accessToken.setType("jsapi_ticket");
            accessTokenDao.save(jsapiticket) ;
        }
        log.info("WeixiProperty.JSAPITICKET = " + WeixiProperty.JSAPITICKET);
    }

}
