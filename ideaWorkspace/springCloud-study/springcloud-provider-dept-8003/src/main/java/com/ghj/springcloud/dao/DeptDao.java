package com.ghj.springcloud.dao;

import com.ghj.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 86187
 */
@Mapper
@Repository
public interface DeptDao {

    public void addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}
