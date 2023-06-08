package com.ghj.api.service;

import com.ghj.api.entity.User;

import java.util.List;

/**
 * @author ghj
 * RPC接口， 提供user服务
 */
public interface UserProviderService {
    /**
     * 获取user
     * @return
     */
    List<User> getUsers();
}
