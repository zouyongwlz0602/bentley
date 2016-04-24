package com.spring.bently.wx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wgq on 16-4-10.
 */
public class DateUtils {

    /**
     * String类型字符串转成date类型  yyyy-MM-dd HH:mm
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String str) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
        Date date = sdf.parse(str) ;
        return date ;
    }

    /**
     * 比较两个Date类型时间,date2 > date1 返回true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equalDateTime(Date date1, Date date2) {
        if(date1 == null || date1 == null){
            return false;
        }
        if(date1.getTime() >= date2.getTime()) {
            return false ;
        }

        return true ;
    }

    /**
     * 根据给定时间和月的数量，得到新的时间
     * @param date
     * @param month
     * @return
     */
    public static Date dateAddMonth(Date date, int month) {

        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH) + month);

        return calendar.getTime() ;
    }
}
