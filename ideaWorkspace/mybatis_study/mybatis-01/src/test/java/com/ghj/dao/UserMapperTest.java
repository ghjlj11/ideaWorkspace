package com.ghj.dao;

import com.ghj.pojo.User;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUserList();
        for(User user : users){
            if(user.getId()>=2){
                System.out.println(user);
            }
        }
        sqlSession.close();
    }
    @Test
    public void test02(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUserList();
        for(User user : users){
            user.setName("郭欢军");
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getById(1);
        System.out.println(user);
        sqlSession.close();
    }
    @Test
    public void test04(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        for(int i = 4; i < 10; i ++){
            userMapper.addUser(new User(i, "是的", i * 12));
        }
        sqlSession.commit();
        List<User> list = userMapper.getUserList();
        for(User users : list){
            System.out.println(users);
        }
        sqlSession.close();
    }
    @Test
    public void test05(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(9);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void test06(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUser(new User(6, "是傻逼" , 10086));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test07(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id",4);
        map.put("keshi", 1243);
        userMapper.updateUser2(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test

    public void test08(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.getUserLike("%是%");
        for( User user : users){
            System.out.println(user);
        }
        sqlSession.close();
    }
}
