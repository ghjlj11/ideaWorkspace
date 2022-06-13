package com.ghj.service.Impl;

import com.ghj.mapper.DepartmentMapper;
import com.ghj.pojo.Department;
import com.ghj.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentMapper.deleteDepartment(id);
    }

    @Override
    public Department getDepartment(int id) {
        return departmentMapper.getDepartment(id);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }
}
