package com.ghj.service;

import com.ghj.mapper.UserMapper;
import com.ghj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        System.out.println("执行了selectAll方法");
        return userMapper.selectAll();
    }

    @Override
    public User selectById(int id) {
        System.out.println("执行了selectById方法");
        return userMapper.selectById(id);
    }

    @Override
    public void update(User user) {
        System.out.println("执行了update方法");
        userMapper.update(user);
    }

    @Override
    public void delete(int id) {
        System.out.println("执行了delete方法");
        userMapper.delete(id);
    }

    @Override
    public void add(User user) {
        System.out.println("执行了add方法");
        userMapper.add(user);
    }
}
