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

/**
 * 功能描述:  <p>
 * 系统用户实体
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 用户密码
     */
    @NotNull
    @Column(length = 20)
    private String password;

    // The user's name
    /**
     * 用户名称
     */
    @NotNull
    @Column(length = 20)
    private String name;

    /**
     * 是否是管理员
     */
    @Column(length = 1)
    private Boolean isAdmin;

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}




