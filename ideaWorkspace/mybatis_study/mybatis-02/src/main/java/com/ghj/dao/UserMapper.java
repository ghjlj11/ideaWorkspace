package com.ghj.dao;

import com.ghj.pojo.User;

import java.util.List;

/**
 * @author 86187
 */
public interface UserMapper {
    static int K = 0;
    List<User> getUserList();

    User getById(int id);

    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

}
