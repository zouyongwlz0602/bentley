package com.spring.bently.manager.utils;

import com.spring.bently.manager.dao.UserDao;
import com.spring.bently.manager.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zouyong on 16/4/16.
 */
public class UserUtil {

    public static User getCurrentUser(HttpServletRequest request,UserDao userDao){
        User user = new User();
        Cookie[] cookieArray = request.getCookies();
        String userName = "";
        if(cookieArray == null ){
            return  user;
        }
        for(Cookie cookie : cookieArray){
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if("userName".equals(cookieName)){
                userName = cookieValue;
            }
        }

        if("".equals(userName)){
            return  user;
        }
        user = userDao.findByName(userName);
        return user;


    }
}
