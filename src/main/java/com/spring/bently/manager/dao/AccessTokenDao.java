package com.spring.bently.manager.dao;

import com.spring.bently.manager.model.AccessToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wgq on 16-4-9.
 */
public interface AccessTokenDao extends CrudRepository<AccessToken, Long> {
    public AccessToken findByType(String type) ;
}
