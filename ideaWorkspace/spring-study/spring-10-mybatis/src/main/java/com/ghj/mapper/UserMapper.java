package com.ghj.mapper;

import com.ghj.pojo.User;

import java.util.List;

/**
 * @author 86187
 */
public interface UserMapper {
    /**
     * 查询全部
     * @return
     */
    public List<User> select();

    /**
     * 新增
     * @param user
     * @return
     */
    int insert(User user);
}
