/*
 * Create Author  : yong.zou
 * Create Date    : 2016-04-09
 * Project        : bently
 * File Name      : UserTestDao.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */
package com.spring.bently.manager.dao;

import com.spring.bently.manager.model.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * 功能描述:  <p>
 *
 * @author : yong.zou <p>
 * @version 1.0 2016-04-09
 * @since bently 1.0
 */
public interface UserActivityDao extends CrudRepository<UserActivity, Long> {

    public Page<UserActivity> findAll(Pageable pageRequest) ;
}
