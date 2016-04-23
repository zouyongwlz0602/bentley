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
 * 活动报名表
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "activitysign")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivitySign {

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
    private String wechatname;

    /**
     * 活动ID
     */
    @Column(length = 5)
    private int activityId;

    /**
     * 活动名称
     */
    @Column(length = 300)
    private String activityTitle;



    public ActivitySign(){}

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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }
}





