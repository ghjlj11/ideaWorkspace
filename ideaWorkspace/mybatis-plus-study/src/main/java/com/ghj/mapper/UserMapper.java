package com.ghj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ghj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ghj
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
