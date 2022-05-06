package com.ghj.mapper;

import com.ghj.pojo.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 86187
 */
@Mapper
@Repository
public interface PeopleMapper {
    public People selectByName(String name);

    public List<People> selectAll();
}
