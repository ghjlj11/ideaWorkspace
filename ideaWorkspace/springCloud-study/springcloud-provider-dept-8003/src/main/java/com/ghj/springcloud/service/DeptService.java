package com.ghj.springcloud.service;

import com.ghj.springcloud.pojo.Dept;

import java.util.List;

/**
 * @author 86187
 */
public interface DeptService {

    public void addDept(Dept dept);

    public Dept queryById(Long id);

    public List<Dept> queryAll();
}
