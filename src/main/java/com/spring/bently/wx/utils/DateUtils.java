package com.spring.bently.wx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wgq on 16-4-10.
 */
public class DateUtils {

    public static Date stringToDate(String str) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm") ;
        Date date = sdf.parse(str) ;
        return date ;
    }
}
