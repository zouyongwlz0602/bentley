/*
 * Create Author  : yong.zou
 * Create Date    : 2016-04-09
 * Project        : bently
 * File Name      : FastNew.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */
package com.spring.bently.manager.controller;

import com.google.common.collect.Lists;
import com.spring.bently.manager.dao.ActivityDestineDao;
import com.spring.bently.manager.dao.HotelDestineDao;
import com.spring.bently.manager.dao.UserDao;
import com.spring.bently.manager.dao.YachtDestineDao;
import com.spring.bently.manager.model.ActivityDestine;
import com.spring.bently.manager.model.HotelDestine;
import com.spring.bently.manager.model.YachtDestine;
import com.spring.bently.manager.pagedata.BentlyResponse;
import com.spring.bently.manager.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述:  <p>
 *
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@RestController
public class DestineController {

    @Autowired
    private HotelDestineDao hotelDestineDao;

    @Autowired
    private YachtDestineDao yachtDestineDao;

    @Autowired
    private ActivityDestineDao activityDestineDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/get/hotelList")
    public BentlyResponse getHotelList(){
        List<HotelDestine> list = Lists.newArrayList();
        Iterator<HotelDestine> iterable = hotelDestineDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/hotel")
    public BentlyResponse handleHotel(long id,HttpServletRequest request){

        HotelDestine hotelDestine = hotelDestineDao.findOne(id);
        hotelDestine.setIsHandle(true);
        hotelDestine.setHandleuser(UserUtil.getCurrentUser(request, userDao).getName());
        hotelDestine.setUpdateTime(new Date());
        HotelDestine result = hotelDestineDao.save(hotelDestine);
        if(result == null){
            return BentlyResponse.fail("更新数据异常，请稍后重试！");
        }else{
            return BentlyResponse.success(hotelDestine);
        }

    }


    @RequestMapping("/get/yachtList")
    public BentlyResponse getYachtList(){
        List<YachtDestine> list = Lists.newArrayList();
        Iterator<YachtDestine> iterable = yachtDestineDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/yacht")
    public BentlyResponse handleYacht(long id,HttpServletRequest request){

        YachtDestine yachtDestine = yachtDestineDao.findOne(id);
        yachtDestine.setIsHandle(true);
        yachtDestine.setHandleuser(UserUtil.getCurrentUser(request, userDao).getName());
        yachtDestine.setUpdateTime(new Date());
        YachtDestine result = yachtDestineDao.save(yachtDestine);
        if(result == null){
            return BentlyResponse.fail("更新数据异常，请稍后重试！");
        }else{
            return BentlyResponse.success(yachtDestine);
        }

    }


    @RequestMapping("/get/activityDestineList")
    public BentlyResponse getActivityList(){
        List<ActivityDestine> list = Lists.newArrayList();
        Iterator<ActivityDestine> iterable = activityDestineDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/activityDestine")
    public BentlyResponse handleActivity(long id, HttpServletRequest request){

        ActivityDestine activityDestine = activityDestineDao.findOne(id);
        activityDestine.setIsHandle(true);
        activityDestine.setHandleuser(UserUtil.getCurrentUser(request, userDao).getName());
        activityDestine.setUpdateTime(new Date());
        ActivityDestine result = activityDestineDao.save(activityDestine);
        if(result == null){
            return BentlyResponse.fail("更新数据异常，请稍后重试！");
        }else{
            return BentlyResponse.success(activityDestine);
        }

    }



}
