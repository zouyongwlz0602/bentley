package com.spring.bently.wx.utils;

import com.spring.bently.wx.utils.httptool.CustomHttpConnection;
import com.spring.bently.wx.utils.httptool.HttpConnectionCommon;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wgq on 16-3-31.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str)) {
            return true ;
        }
        return false ;
    }

    public static String replaceEach(String url, String ... values) {

        //String reg = "{[]}" ;
        for(String value : values) {
            url = url.replaceFirst("\\{\\[\\]\\}",value) ;
        }
        return url ;

        //return org.apache.commons.lang3.StringUtils.replaceEach(url,new String[]{"{[]}"},values) ;
    }

    //得到真实的ip地址
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    //生产订单id
    public static String generateOrderId() {
        Random random = new Random(100) ;
        int id = random.nextInt(999) ;

        long time = System.currentTimeMillis() ;

        return "" + time + id ;
    }

    public static String StringFilter(String str)   throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx="[`-~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static String create_nonce_str() {
        String ss = UUID.randomUUID().toString().replaceAll("-","") ;
        if(ss.length()>32) {
            ss = ss.substring(0,31) ;
        }
        return ss ;
    }

    //eg.http://qq.com
    public static String getWebServerName(HttpServletRequest request) {
        return request.getScheme()+"://"+request.getServerName();
    }

    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getWeixiCallBackUrl(HttpServletRequest request,String path) {
        StringBuilder builder = new StringBuilder(request.getScheme()+"://"+request.getServerName()+path) ;
        String code = request.getParameter("code") ;
        String state = request.getParameter("state") ;
        if(state.indexOf("#") != -1) {
            state = state.replace("#","%23") ;
            //state = state.substring(0,state.indexOf("#")) ;
        }
        builder.append("?code=").append(code).append("&state=").append(state) ;
        return builder.toString() ;
    }

    //如果传入的参数是数组
    public static String getUrl(HttpServletRequest request,String path) {
        StringBuilder builder = new StringBuilder(request.getScheme()+"://"+request.getServerName()+"/wx/member/carwash") ;
        Map<String,String[]> map = request.getParameterMap() ;
        if(map != null && map.size() > 0) {
            builder.append("?") ;
        }
        else {
            return builder.toString() ;
        }
        for(String key:map.keySet()) {
            String[] array = map.get(key) ;
            String value = array[0] ;
            builder.append(key).append("=").append(array[0]).append("&") ;
        }
        builder.deleteCharAt(builder.length() - 1) ;
        return builder.toString() ;
    }

    //微信得到的经纬度转换成百度的经纬度
    public static String getBaiDuLocationXY(String x, String y) {
        String result = "";
        String url = "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
                + x + "&y=" + y + "";
        CustomHttpConnection customHttpConnection = new CustomHttpConnection(new HttpConnectionCommon(url,"GET")) ;
        String response = customHttpConnection.httpClient(null) ;
        if (!StringUtils.isEmpty(response)) {
            Map map = JsonUtils.jsonToMap(response) ;
            if (map != null && "0".equals(String.valueOf(map.get("error")))) {
               // byte[] xbuff = Base64.decodeFast(map.get("x"));
               // byte[] ybuff = Base64.decodeFast(map.get("y"));
                result = getFromBase64(map.get("x").toString()) + "|" + getFromBase64(map.get("y").toString());
            }
        }
        return result;
    }

    //根据百度经纬度得打地址，，地址有误差，误差根据pois
    //x 纬度  y精度
    public static String getLocationName(String x, String y,String pois) {
        String ss = getBaiDuLocationXY(x,y) ;
        String[] arr = ss.split("\\|") ;
        String url = "http://api.map.baidu.com/geocoder/v2/?location=" + arr[0] + "," + arr[1] + "&output=json&ak=9CSMnOb7VMbWQ5GQLMtR2gZoG0KS2Zcn&pois="+pois;
        CustomHttpConnection customHttpConnection = new CustomHttpConnection(new HttpConnectionCommon(url,"GET")) ;
        String json = customHttpConnection.httpClient(null) ;
        return json ;
    }

    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            //Base64 base64 = new Base64();
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateOrderId());
    }
}
