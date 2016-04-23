package com.spring.bently.wx.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by wgq on 16-4-4.
 */
public class WeixinPropertiesUtils {

    private static Properties prop ;

    static {
        prop = new Properties() ;
        InputStream in = null ;
        InputStream in2 = null ;
        try {
            in = new BufferedInputStream(WeixinPropertiesUtils.class.getResourceAsStream("/wx/weixin.properties")) ;
            prop.load(in);
            in2 = new BufferedInputStream(WeixinPropertiesUtils.class.getResourceAsStream("/wx/message.properties")) ;
            prop.load(in2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in2 != null) {
                try {
                    in2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperties(String key) {
        return prop.getProperty(key) ;
    }

    public static void setProperties(String key, String value) {
        prop.setProperty(key, value) ;
    }
}
