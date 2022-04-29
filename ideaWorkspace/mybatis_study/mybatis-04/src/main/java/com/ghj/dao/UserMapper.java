package com.ghj.dao;

import com.ghj.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.text.html.HTML.Tag.SELECT;

/**
 * @author 86187
 */
public interface UserMapper {


    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where id = #{id}")
    User selectId(@Param("id") String id);

    @Insert("insert into user (id, name, age) values(#{id},#{name},#{time})")
    void addUser(User user);

    @Update("update  user set name = #{name}, age = #{time} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(@Param("id") String id);
}
