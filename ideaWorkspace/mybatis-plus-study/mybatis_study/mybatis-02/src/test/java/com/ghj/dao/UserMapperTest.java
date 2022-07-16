package com.ghj.dao;

import com.ghj.pojo.User;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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
}
