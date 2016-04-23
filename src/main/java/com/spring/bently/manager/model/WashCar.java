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
 * 上门洗车预订
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "washcar")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WashCar {

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
     * 预订洗车时间
     */
    @Column
    private Date destinetime;

    /**
     * 地点
     */
    @Column(length = 100)
    private String address;

    /**
     * 办理人名称
     */
    @Column(length = 20)
    private String handleuser;

    /**
     * 是否办理
     */
    private Boolean isHandle;

    /**
     *  创建时间
     */
    @Column
    private Date updateTime;



    public WashCar(){}

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

    public Date getDestinetime() {
        return destinetime;
    }

    public void setDestinetime(Date destinetime) {
        this.destinetime = destinetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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




