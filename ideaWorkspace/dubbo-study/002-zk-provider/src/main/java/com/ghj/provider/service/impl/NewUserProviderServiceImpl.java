package com.ghj.provider.service.impl;


import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;

import java.util.ArrayList;
import java.util.List;

/**
 * 新版本RPC接口实现类
 * @author 86187
 */
public class NewUserProviderServiceImpl implements UserProviderService {

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(6666L, 6, 66, "newVersion"));
        return list;
    }
}
