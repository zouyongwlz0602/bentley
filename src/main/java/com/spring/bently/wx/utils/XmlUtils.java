package com.spring.bently.wx.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wgq on 16-4-4.
 */
public class XmlUtils {

    //微信xml-to-map
    public static Map<String,String> xmlToMap(InputStream inputStream) {
        SAXReader reader = new SAXReader() ;
        reader.setEncoding("UTF-8");
        Map<String,String> map = new HashMap<String,String>() ;
        try {
            Document document = reader.read(inputStream) ;
            System.out.println(document.asXML());
            Element root = document.getRootElement() ;
            for(Iterator it = root.elementIterator();it.hasNext();) {
                Element element = (Element) it.next() ;
                map.put(element.getName().toLowerCase(),element.getText()) ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map ;
    }

    public static Map<String,String> xmlToMapString(String message) {
        Document document = null;
        Map<String,String> map = new HashMap<String,String>() ;
        try{
            document = DocumentHelper.parseText(message) ;
            Element root = document.getRootElement() ;
            for(Iterator it = root.elementIterator();it.hasNext();) {
                Element element = (Element) it.next() ;
                map.put(element.getName(),element.getText()) ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            document.clearContent();
            document = null ;
        }

        return map ;
    }

    public static String mapToXml(Map<String,Object> map) {

        StringBuilder sb = new StringBuilder("<xml>") ;

        for(String key : map.keySet()) {

            sb.append("<").append(key).append(">") ;
            Object value = map.get(key) ;
            if(value instanceof String) {
                sb.append("![CDATA[").append(value).append("]]") ;
            }else if(value instanceof Integer) {
                sb.append(value) ;
            }else if(value instanceof Long) {
                sb.append(value) ;
            }
            sb.append("</").append(key).append(">") ;
        }
        sb.append("</xml>") ;

        return sb.toString() ;

    }

    public static String mapToXmlTwo(Map<String,Object> map) {
        StringBuilder sb = new StringBuilder("<xml>") ;

        for(String key : map.keySet()) {

            sb.append("<").append(key).append(">") ;
            if("notify_url".equals(key)) {
                sb.append(StringEscapeUtils.escapeXml10(map.get(key).toString())) ;
               // System.out.println(StringEscapeUtils.escapeXml10(map.get(key).toString()));
            }else {
                sb.append(map.get(key).toString()) ;
            }
            sb.append("</").append(key).append(">") ;
        }
        sb.append("</xml>") ;
        return sb.toString() ;
    }
}
