package com.ghj.service;

import com.ghj.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86187
 */
public interface UserService {
    public List<User> selectAll();
    public User selectById(int id);
    public void update(User user);
    public void delete(int id);
    public void add(User user);
}
