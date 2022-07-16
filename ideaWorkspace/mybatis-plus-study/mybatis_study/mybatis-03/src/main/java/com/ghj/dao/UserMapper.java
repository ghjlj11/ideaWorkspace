package com.ghj.dao;

import com.ghj.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public interface UserMapper {
    List<User> getUserList();

    User getById(int id);

    void addUser(User user);

    List<User> selectLimit(Map<String, Integer> map);
}
