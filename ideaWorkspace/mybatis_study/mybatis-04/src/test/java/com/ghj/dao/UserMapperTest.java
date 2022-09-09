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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /**
         *         List<User> users = mapper.selectAll();
         *         for (User user : users) {
         *             System.out.println(user);
         *         }
         *         User user = mapper.selectId("2234567");
         *         System.out.println(user);
         *         mapper.updateUser(new User("4","是你", 99));
         *         mapper.deleteUser("4");
         */

        mapper.selectAll();
        sqlSession.close();
    }
}
