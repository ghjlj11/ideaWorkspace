package com.ghj.mapper;

import com.ghj.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author 86187
 */
public class UserMapperImpl implements UserMapper{

    private SqlSessionTemplate sqlSession ;


    @Override
    public List<User> select() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.select();
    }

    @Override
    public int insert(User user) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.insert(user);
    }


    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
}
