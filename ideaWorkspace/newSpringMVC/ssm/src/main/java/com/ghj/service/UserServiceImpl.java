package com.ghj.service;

import com.ghj.mapper.UserMapper;
import com.ghj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 86187
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }
}
