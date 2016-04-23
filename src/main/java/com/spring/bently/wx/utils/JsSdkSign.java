package com.spring.bently.wx.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wgq on 16-4-12.
 */
public class JsSdkSign {
    public static void main(String[] args) {
        String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    public static Map<String, String> getSign(HttpServletRequest request,String url) {
       // String url = request.getScheme()+"://"+request.getServerName()+"/wx/member/recharge";
        //System.out.println("url = " + url);
       // AccessToken jsapiticket = accessTokenDao.findByType("jsapi_ticket") ;
        Map<String, String> map = JsSdkSign.sign(WeixiProperty.JSAPITICKET, url) ;
        map.put("appid", WeixinPropertiesUtils.getProperties("appid")) ;
        return map ;
    }

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    //按照微信规则生成pre_pay sign
    public static String getPrePaySign(Map<String,Object> map, String wxkey) {
        StringBuilder builder = new StringBuilder() ;
        for(String key:map.keySet()) {
            System.out.println("key = " + key);
            System.out.println("value = " + map.get(key));
            builder.append(key).append("=").append(map.get(key)).append("&") ;
        }
        //builder.deleteCharAt(builder.length() - 1) ;
        builder.append("key=").append(wxkey) ;
        System.out.println("builder = " + builder.toString());
        String sign = StringUtils.MD5(builder.toString()).toUpperCase() ;
        System.out.println("sign = " + sign);
        return sign ;
    }
}
