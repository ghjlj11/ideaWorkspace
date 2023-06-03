package com.ghj.provider.service.impl;


import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class UserProviderServiceImpl implements UserProviderService {
    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User(123L, 1, 23, "shanghai"));
        list.add(new User(456L, 2, 88, "beijing"));
        return list;
    }
}
