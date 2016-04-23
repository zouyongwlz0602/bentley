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
import com.google.common.collect.Maps;
import com.spring.bently.manager.dao.*;
import com.spring.bently.manager.model.*;
import com.spring.bently.manager.pagedata.BentlyResponse;
import com.spring.bently.manager.utils.UserUtil;
import com.spring.bently.wx.service.ISendMessageService;
import com.spring.bently.wx.service.IUserGroupService;
import com.spring.bently.wx.service.impl.SendTextMessageService;
import com.spring.bently.wx.service.impl.UserGroupService;
import com.spring.bently.wx.utils.JsonUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述:  <p>
 *
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@RestController
public class MemberCenterController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private WashCarDao washCarDao;

    @Autowired
    private WaxingDao waxingDao;

    @Autowired
    private MaintenanceDao maintenanceDao;

    @Autowired
    private RescueDao rescueDao;

    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(MemberCenterController.class);

    @RequestMapping("/get/washCarList")
    public BentlyResponse getHotelList(){
        List<WashCar> list = Lists.newArrayList();
        Iterator<WashCar> iterable = washCarDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/washCar")
    public BentlyResponse handleHotel(long id,HttpServletRequest request){

        WashCar washCar = washCarDao.findOne(id);
        Member member = memberDao.findByWechatid(washCar.getWechatid());
        if(member == null){
            return BentlyResponse.fail("会员不存在!");
        }
        int num = member.getWashcarnum();
        if(num<=0){
            return BentlyResponse.fail("剩余洗车次数为0,不能操作!");
        }
        member.setWashcarnum(num-1);
        Member memberResult = memberDao.save(member);
        if(memberResult == null){
            return BentlyResponse.fail("保存洗车次数失败!");
        }

        washCar.setIsHandle(true);
        washCar.setHandleuser(UserUtil.getCurrentUser(request,userDao).getName());
        washCar.setUpdateTime(new Date());
        WashCar result = washCarDao.save(washCar);
        if(result == null){
            return BentlyResponse.fail("更新洗车数据失败，请稍后重试！");
        }else{
            return BentlyResponse.success(result);
        }

    }



    @RequestMapping("/get/waxingList")
    public BentlyResponse getWaxingList(){
        List<Waxing> list = Lists.newArrayList();
        Iterator<Waxing> iterable = waxingDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/waxing")
    public BentlyResponse handleWaxing(long id,HttpServletRequest request){

        Waxing waxing = waxingDao.findOne(id);
        Member member = memberDao.findByWechatid(waxing.getWechatid());
        if(member == null){
            return BentlyResponse.fail("会员不存在!");
        }
        int num = member.getWaxingnum();
        if(num<=0){
            return BentlyResponse.fail("剩余打蜡次数为0,不能操作!");
        }
        member.setWaxingnum(num-1);
        Member memberResult = memberDao.save(member);
        if(memberResult == null){
            return BentlyResponse.fail("保存打蜡次数失败!");
        }

        waxing.setIsHandle(true);
        waxing.setHandleuser(UserUtil.getCurrentUser(request,userDao).getName());
        waxing.setUpdateTime(new Date());
        Waxing result = waxingDao.save(waxing);
        if(result == null){
            return BentlyResponse.fail("更新打蜡数据失败，请稍后重试！");
        }else{
            return BentlyResponse.success(result);
        }

    }


    @RequestMapping("/get/maintenanceList")
    public BentlyResponse getMaintenanceList(){
        List<Maintenance> list = Lists.newArrayList();
        Iterator<Maintenance> iterable = maintenanceDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/maintenance")
    public BentlyResponse handleMaintenance(long id,HttpServletRequest request){

        Maintenance maintenance = maintenanceDao.findOne(id);
        Member member = memberDao.findByWechatid(maintenance.getWechatid());
        if(member == null){
            return BentlyResponse.fail("会员不存在!");
        }
        int num = member.getMaintenancenum();
        if(num<=0){
            return BentlyResponse.fail("剩余保养次数为0,不能操作!");
        }
        member.setMaintenancenum(num-1);
        Member memberResult = memberDao.save(member);
        if(memberResult == null){
            return BentlyResponse.fail("保存保养次数失败!");
        }

        maintenance.setIsHandle(true);
        maintenance.setHandleuser(UserUtil.getCurrentUser(request, userDao).getName());
        maintenance.setUpdateTime(new Date());
        Maintenance result = maintenanceDao.save(maintenance);
        if(result == null){
            return BentlyResponse.fail("更新保养数据失败，请稍后重试！");
        }else{
            return BentlyResponse.success(result);
        }

    }


    @RequestMapping("/get/rescueList")
    public BentlyResponse getRescueList(){
        List<Rescue> list = Lists.newArrayList();
        Iterator<Rescue> iterable = rescueDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/handle/rescue")
    public BentlyResponse handleRescue(long id,HttpServletRequest request){

        Rescue rescue = rescueDao.findOne(id);
        Member member = memberDao.findByWechatid(rescue.getWechatid());
        if(member == null){
            return BentlyResponse.fail("会员不存在!");
        }
        int num = member.getIndoorrenum();
        if(num<=0){
            return BentlyResponse.fail("剩余市内救援次数为0,不能操作!");
        }
        member.setIndoorrenum(num-1);
        Member memberResult = memberDao.save(member);
        if(memberResult == null){
            return BentlyResponse.fail("保存市内救援次数失败!");
        }

        rescue.setIsHandle(true);
        rescue.setHandleuser(UserUtil.getCurrentUser(request, userDao).getName());
        rescue.setUpdateTime(new Date());
        Rescue result = rescueDao.save(rescue);
        if(result == null){
            return BentlyResponse.fail("更新市内救援数据失败，请稍后重试！");
        }else{
            return BentlyResponse.success(result);
        }

    }

    @RequestMapping("/get/memberList")
    public BentlyResponse getMemberList(){
        List<Member> list = Lists.newArrayList();
        Iterator<Member> iterable = memberDao.findAll().iterator();
        while(iterable.hasNext()){
            list.add(iterable.next());
        }
        return BentlyResponse.success(list);
    }

    @RequestMapping("/get/member")
    public BentlyResponse getMember(long id){

        Member member =  memberDao.findOne(id);
        if(member == null){
            return BentlyResponse.fail("会员用户不存在");
        }else{
            return BentlyResponse.success(member);
        }
    }

    @RequestMapping("/update/member")
    public BentlyResponse updateMemberList(@RequestBody Member member){
        Member dbMember = memberDao.findOne(member.getId());
        dbMember.setEndTime(member.getEndTime());
        dbMember.setIndoorrenum(member.getIndoorrenum());
        dbMember.setIsVip(member.getIsVip());
        dbMember.setStartTime(member.getStartTime());
        dbMember.setWashcarnum(member.getWashcarnum());
        dbMember.setWaxingnum(member.getWaxingnum());
        dbMember.setMaintenancenum(member.getMaintenancenum());
        Member result = memberDao.save(dbMember);
        if(result == null){
            return BentlyResponse.fail("更新会员数据失败，请稍后重试！");
        }else{
            logger.info("会员信息操作:"+dbMember.toString());
            return BentlyResponse.success(result);
        }
    }

    @RequestMapping("/groups")
    public BentlyResponse getGroup(){

        IUserGroupService userGroupService = new UserGroupService();
        Map map  = JsonUtils.jsonToMap(userGroupService.listGroup());
        if(map.size() == 0){
            return BentlyResponse.fail("请先设置会员分组");
        }
        ArrayList groupList = (ArrayList)map.get("groups");
        if(groupList == null){
            return BentlyResponse.fail("请先设置会员分组");
        }

        if(groupList.size() == 0){
            return BentlyResponse.fail("请先设置会员分组");
        }
        return BentlyResponse.success(groupList);
    }

    @RequestMapping("/sendMessage")
    public BentlyResponse sendMessage(long id, String context){

        ISendMessageService sendMessageService = new SendTextMessageService();
        Map<String,Object> map = Maps.newHashMap();
        map.put("group_id",id);
        map.put("context",context);

        String result = sendMessageService.sendMessage(map);

        Map resultMap = JsonUtils.jsonToMap(result);

        int code = (Integer)resultMap.get("errcode");
        if(code == 0){
            return BentlyResponse.success("消息发送成功");
        }else{
            return BentlyResponse.fail("消息发送失败");
        }

    }




}
