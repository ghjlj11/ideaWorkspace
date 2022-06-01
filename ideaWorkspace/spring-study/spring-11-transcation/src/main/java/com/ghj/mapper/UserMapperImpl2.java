package com.ghj.mapper;

import com.ghj.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 86187
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public List<User> select() {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(2, "罗静", 21);
        mapper.delete(1);
        mapper.add(user);
        return mapper.select();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public int add(User user) {
        SqlSession sqlSession = this.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.add(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
    public int delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
