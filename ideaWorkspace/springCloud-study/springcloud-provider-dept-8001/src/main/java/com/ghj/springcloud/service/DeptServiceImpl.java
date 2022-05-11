package com.ghj.springcloud.service;

import com.ghj.springcloud.dao.DeptDao;
import com.ghj.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */
@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    DeptDao deptDao;
    @Override
    public void addDept(Dept dept) {
        deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}
