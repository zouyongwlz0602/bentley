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
 *  俱乐部动态
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "clubdynamic")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubDynamic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 俱乐部动态标题
     */
    @NotNull
    @Column(length = 300)
    private String title;


    /**
     * 动态内容
     */
    @Column(length = 1000)
    private String context;

    /**
     * 更新时间
     */
    @Column
    private Date updateTime;

    /**
     * 更新人名称
     */
    @Column(length = 20)
    private String handleuser;

    @Override
    public String toString() {
        return "ClubDynamic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", updateTime=" + updateTime +
                ", handleuser='" + handleuser + '\'' +
                '}';
    }

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
}




