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
 * 游艇预订实体
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "yachtdestine")
@JsonIgnoreProperties(ignoreUnknown = true)
public class YachtDestine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 微信ID
     */
    @NotNull
    @Column(length = 50)
    private String wechatid;


    /**
     * 微信名称
     */
    @Column(length = 30)
    private String wechatname;

    /**
     * 预订天数
     */
    @Column(length = 3)
    private int daynum;

    /**
     * 预订时间
     */
    @Column
    private Date destinetime;

    /**
     * 办理人名称
     */
    @Column(length = 20)
    private String handleuser;


    /**
     * 创建时间
     */
    @Column(length = 20)
    private Date updateTime;

    /**
     * 是否办理
     */
    @Column(length = 5)
    private Boolean isHandle;


    public YachtDestine(){}


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

    public int getDaynum() {
        return daynum;
    }

    public void setDaynum(int daynum) {
        this.daynum = daynum;
    }

    public Date getDestinetime() {
        return destinetime;
    }

    public void setDestinetime(Date destinetime) {
        this.destinetime = destinetime;
    }

    public String getHandleuser() {
        return handleuser;
    }

    public void setHandleuser(String handleuser) {
        this.handleuser = handleuser;
    }

    public Boolean getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(Boolean isHandle) {
        this.isHandle = isHandle;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}




