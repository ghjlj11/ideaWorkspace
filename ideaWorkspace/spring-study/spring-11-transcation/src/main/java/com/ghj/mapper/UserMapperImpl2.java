package com.ghj.mapper;

import com.ghj.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author 86187
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> select() {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(2, "罗静", 21);
        mapper.add(user);
        mapper.delete(1);
        return mapper.select();
    }

    @Override
    public int add(User user) {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.add(user);
    }

    @Override
    public int delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
