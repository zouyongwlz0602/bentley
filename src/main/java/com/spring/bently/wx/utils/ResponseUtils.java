package com.spring.bently.wx.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wgq on 16-4-10.
 */
public class ResponseUtils {
    public static String textResponse(Map<String,String> map, String returnValue) {
        Map<String,Object> returnMap = new HashMap<String,Object>() ;
        returnMap.put("ToUserName",map.get("fromusername")) ;
        returnMap.put("FromUserName",map.get("tousername")) ;
        returnMap.put("CreateTime", Calendar.getInstance().getTimeInMillis() / 1000) ;
        returnMap.put("MsgType","text") ;
        returnMap.put("Content",returnValue) ;

        StringBuffer sb = new StringBuffer() ;
        sb.append("<xml>");
        sb.append("<ToUserName><![CDATA[" + map.get("fromusername") + "]]></ToUserName>");
        sb.append("<FromUserName><![CDATA[" + map.get("tousername") + "]]></FromUserName>");
        sb.append("<CreateTime>" + Calendar.getInstance().getTimeInMillis() / 1000 + "</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[" + returnValue + "]]></Content>");
        sb.append("</xml>");



        return sb.toString() ;
        //return XmlUtils.mapToXml(returnMap) ;
    }

    public static <T> String newsResponse(Map<String,String> map,List<T> list) {
        StringBuffer sb = new StringBuffer() ;

        sb.append("<xml>") ;
        sb.append("<ToUserName><![CDATA["+ map.get("fromusername") +"]]></ToUserName>") ;
        sb.append("<FromUserName><![CDATA["+ map.get("tousername") + "]]></FromUserName>");
        sb.append("<CreateTime>"+ Calendar.getInstance().getTimeInMillis() / 1000 +"</CreateTime>");
        sb.append("<MsgType><![CDATA[news]]></MsgType>");
        sb.append("<ArticleCount>" + list.size() +"</ArticleCount>") ;
        sb.append("<Articles>") ;
        for(T t:list) {
            try {
                Method getTitle = t.getClass().getDeclaredMethod("getTitle") ;
                String title = String.valueOf(getTitle.invoke(t)) ;

                Method getContext = t.getClass().getDeclaredMethod("getContext") ;
                String context = String.valueOf(getContext.invoke(t)) ;
                if(context.length()>200) {
                    context = context.substring(0,200) ;
                }

                Method getId = t.getClass().getDeclaredMethod("getId") ;
                String id = String.valueOf(getId.invoke(t)) ;
                sb.append("<item>");
                sb.append("<Title><![CDATA[" + title +"]]></Title>");
                sb.append("<Description><![CDATA[" + context + "...]]></Description>");
               // sb.append("<PicUrl><![CDATA[]]></PicUrl>");
                sb.append("<Url><![CDATA[" + map.get("url") + id +"]]></Url>");
                sb.append("</item>");

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append("</Articles>") ;
        sb.append("</xml>") ;

        return sb.toString() ;

    }
}
