package com.ghj.dao;

import com.ghj.pojo.User;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class UserMapperTest {
    static Logger logger = Logger.getLogger(UserMapperTest.class);
    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.addUser(new User(6, "语文", 77));
        User use = userMapper.getById(3);
        System.out.println(use);
        sqlSession.close();
    }
    @Test
    public void testLog4j(){
        logger.info("info : 进入log4j的info");
        logger.debug("debug: 进入log4j的debug");
        logger.error("error : 进入log4j的error");
    }

    @Test
    public void testLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Integer> map = new HashMap<>();
        map.put("starIndex", 2);
        map.put("pageSize", 3);
        List<User> users = userMapper.selectLimit(map);
        for (User user: users) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}
