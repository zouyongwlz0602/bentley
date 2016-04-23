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
 * 用户活动
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "useractivity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 活动标题
     */
    @NotNull
    @Column(length = 100)
    private String title;


    /**
     * 活动内容
     */
    @Column(length = 1000)
    private String context;

    /**
     * 活动开始时间
     */
    @Column
    private Date startTime;

    /**
     * 活动结束时间
     */
    @Column
    private Date endTime;

    /**
     * 活动更新时间
     */
    @Column
    private Date updateTime;

    /**
     * 办理人名称
     */
    @Column(length = 20)
    private String handleuser;

    public UserActivity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHandleuser() {
        return handleuser;
    }

    public void setHandleuser(String handleuser) {
        this.handleuser = handleuser;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}




