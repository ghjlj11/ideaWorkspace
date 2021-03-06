package com.ghj.dao;

import com.ghj.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public interface UserMapper {
    List<User> getUserList();

    List<User> getUserLike(String s);

    User getById(int id);
    User getByIdName(@Param("name") String name ,@Param("id") int id);

    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    void updateUser2(Map<String, Integer> map);
}
