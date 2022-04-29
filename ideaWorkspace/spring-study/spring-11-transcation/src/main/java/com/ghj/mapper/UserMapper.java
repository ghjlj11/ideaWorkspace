package com.ghj.mapper;

import com.ghj.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86187
 */
public interface UserMapper {
    public List<User> select();

    public int add( User user);

    public int delete(@Param("id") int id);
}
