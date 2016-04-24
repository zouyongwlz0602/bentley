package com.wx.test;

import com.spring.bently.wx.utils.JsSdkSign;
import com.spring.bently.wx.utils.WeixinPropertiesUtils;
import com.spring.bently.wx.utils.XmlUtils;
import com.spring.bently.wx.utils.httptool.CustomHttpConnection;
import com.spring.bently.wx.utils.httptool.CustomHttpsConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wgq on 16-4-2.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration
public class MyTest {

    @Test
    public void testHttps() {
        //{"access_token":"Hht0QmyysCpgJpD4WXH_36OymMJW00Xn1kbNU5HVoPodLCKcvDETCa1G9IHDrhse72KPDkAqXmIznPLLmVKkQ6Wz2MxxpNzRKEE4vEbCpXvUWFK83ohEgDuH5M_65e8YVLQiAAAFVA","expires_in":7200}
        HttpConnectionCommon hcc =
                new HttpConnectionCommon("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxff3d1c1de2bd91fe&secret=adf7f3377400add0b0b4698af5a1fc58", "GET") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String message = connection.httpsClient(null) ;
        System.out.println(message);
    }

    @Test
    public void testWXProperties() {
        System.out.println(WeixinPropertiesUtils.getProperties("access_token"));
    }

    @Test
    public void testReturn() {
        HttpConnectionCommon hcc =
                new HttpConnectionCommon("http://wgq.tunnel.qydev.com/entrance", "POST") ;
        CustomHttpConnection connection = new CustomHttpConnection(hcc) ;
        StringBuffer buf = new StringBuffer() ;

       // buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>") ;
        buf.append("<xml>");
        buf.append("<ToUserName><![CDATA[gh_6f1ea73d1913]]></ToUserName>");
        buf.append("<FromUserName><![CDATA[oygVcwB3xFoQqOTklmOBcdyiMU50]]></FromUserName>");
        buf.append("<CreateTime>1460230912</CreateTime>");
        buf.append("<MsgType><![CDATA[event]]></MsgType>");
        buf.append("<Event><![CDATA[CLICK]]></Event>");
        buf.append("<EventKey><![CDATA[ykt_destine]]></EventKey>");
        buf.append("</xml>");

        String message = connection.httpClient(buf.toString()) ;
        System.out.println(message);

    }

    @Test
    public void testWxPay() {
        Map<String,Object> map = new TreeMap<String,Object>() ; //使用TreeMap 自动排序
        String key = "bentleyclub99153426benlteyclub99";   //商户支付平台key，用于生成0sign
        map.put("appid", "wxf354b3a19d4fe54b") ;    //appid
        map.put("mch_id", "1327994601") ;  //商户号
        map.put("nonce_str", "2hsdjfhslylljkj24h2ghj4jh2j") ;  //随机字符串
        map.put("device_info", "WEB") ;
        map.put("body", "11") ;  //商品描述
        map.put("out_trade_no", "D12345422392") ;    //商户订单号
        map.put("total_fee", 12 * 30 * 100) ;
        map.put("spbill_create_ip", "127.0.0.1") ;    //终端ip
        //接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
        map.put("notify_url", "http://sss.tunnel.qydev.com/wx/member/recharge/callback") ;
        map.put("trade_type", "JSAPI") ;    //交易类型
        map.put("openid","oygVcwB3xFoQqOTklmOBcdyiMU50") ;    //openid
        String sign = JsSdkSign.getPrePaySign(map, key) ;
        Map<String,Object> requestMap = new LinkedHashMap<String,Object>() ;
        for(String keys:map.keySet()) {
            requestMap.put(keys,map.get(keys)) ;
        }
        requestMap.put("sign", sign) ; //签名

        String postUrl = WeixinPropertiesUtils.getProperties("single_unified_url") ;
        HttpConnectionCommon hcc = new HttpConnectionCommon(postUrl,5000,"POST","text/xml") ;
        CustomHttpsConnection connection = new CustomHttpsConnection(hcc) ;
        String responseMsg = connection.httpsClient(XmlUtils.mapToXmlTwo(requestMap)) ;
        System.out.println(XmlUtils.mapToXmlTwo(requestMap));
        System.out.println("微信返回信息：" + responseMsg);
        if(responseMsg == null) {
        }
        //Map responseMap = XmlUtils.xmlToMapString(responseMsg) ;
    }

    @Test
    public void testSendMessage() {
        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("group_id",100) ;
        map.put("context","测试") ;
        map.put("is_to_all",false) ;
        map.put("group",true);

       // SendTextMessageService sendTextMessageService = new SendTextMessageService() ;
       // String responseMsg = sendTextMessageService.sendMessage(map) ;
        //System.out.println(responseMsg);
    }

    @Test
    public void testGetGroupList() {
        //UserGroupService userGroupService = new UserGroupService() ;
        //System.out.println(userGroupService.listGroup());
    }

    @Test
    public void testGetLocation() {

    /*    String ss = StringUtils.getBaiDuLocationXY("31.288692","121.34873") ;
        System.out.println(ss);
        String[] arr = ss.split("\\|") ;
        System.out.println(arr[0]);
        System.out.println(arr[1]);

        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + arr[0] + "," + arr[1] + "&output=json&ak=9CSMnOb7VMbWQ5GQLMtR2gZoG0KS2Zcn&pois=40";
        CustomHttpConnection customHttpConnection = new CustomHttpConnection(new HttpConnectionCommon(url,"GET")) ;
        String json = customHttpConnection.httpClient(null) ;
        System.out.println(json);*/
    }
}
