package com.ghj.provider.service.impl;

import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;

import java.util.ArrayList;
import java.util.List;

/**
 * RPC接口实现类
 * @author 86187
 */
public class UserProviderServiceImpl implements UserProviderService {
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(12L, 2, 33, "nanchang"));
        list.add(new User(23L, 1, 43, "shanghai"));
        list.add(new User(34L, 2, 53, "beijing"));
        return list;
    }
}
