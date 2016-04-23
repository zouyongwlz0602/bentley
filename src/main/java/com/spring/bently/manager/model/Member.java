/*
 * Create Author  : yong.zou
 * Create Date    : 2016-04-09
 * Project        : bently
 * File Name      : UserTest.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */
package com.spring.bently.manager.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 功能描述:  <p>
 * 会员实体
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 微信ID
     */
    @NotNull
    @Column(length = 30)
    private String wechatid;


    /**
     * 微信名称
     */
    @Column(length = 50)
    private String wechatname;

    /**
     * 会员开始时间
     */
    @Column
    private Date startTime;

    /**
     * 会员结束时间
     */
    @Column
    private Date endTime;


    /**
     * 免费洗车剩余次数
     */
    @Column(length = 10)
    private int washcarnum = 0;

    /**
     * 免费上门打蜡剩余次数
     */
    @Column(length = 10)
    private int waxingnum = 0;

    /**
     * 免费室内救援剩余次数
     */
    @Column(length = 10)
    private int indoorrenum = 0;

    /**
     * 免费保养剩余次数
     */
    @Column(length = 10)
    private int maintenancenum = 0;

    /**
     * 是否会员 true 是， FALSE不是
     */
    @Column
    private Boolean isVip;

    /**
     * 是否订阅 true 是， FALSE不是
     */
    @Column
    private Boolean isSubscribe ;

    public Boolean getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(Boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public Member(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    public String getWechatname() {
        return wechatname;
    }

    public void setWechatname(String wechatname) {
        this.wechatname = wechatname;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getWashcarnum() {
        return washcarnum;
    }

    public void setWashcarnum(int washcarnum) {
        this.washcarnum = washcarnum;
    }

    public int getWaxingnum() {
        return waxingnum;
    }

    public void setWaxingnum(int waxingnum) {
        this.waxingnum = waxingnum;
    }

    public int getIndoorrenum() {
        return indoorrenum;
    }

    public void setIndoorrenum(int indoorrenum) {
        this.indoorrenum = indoorrenum;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }

    public int getMaintenancenum() {
        return maintenancenum;
    }

    public void setMaintenancenum(int maintenancenum) {
        this.maintenancenum = maintenancenum;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", wechatid='" + wechatid + '\'' +
                ", wechatname='" + wechatname + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", washcarnum=" + washcarnum +
                ", waxingnum=" + waxingnum +
                ", indoorrenum=" + indoorrenum +
                ", maintenancenum=" + maintenancenum +
                ", isVip=" + isVip +
                '}';
    }
}




