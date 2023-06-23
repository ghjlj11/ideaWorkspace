package com.ghj.dao;

import com.ghj.pojo.User;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class UserMapperTest {
    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserList();
        User use = userMapper.getById(3);
        System.out.println(use);
        for(User user : userList){
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testLocalDateTime(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(10086);
        user.setName("haha");
        user.setKeshi(32);
        user.setLocalDateTime(LocalDateTime.now());

        userMapper.addUser(user);
        sqlSession.close();

    }
}
