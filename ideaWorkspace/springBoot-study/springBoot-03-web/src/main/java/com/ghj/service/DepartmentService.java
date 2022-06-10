package com.ghj.service;

import com.ghj.pojo.Department;

import java.util.List;

/**
 * 部门的操作
 * @author 86187
 */
public interface DepartmentService {

    /**
     * 添加一个部门
     * @param department
     */
    void addDepartment(Department department);

    /**
     * 通过id删除一个部门
     * @param id
     */
    void deleteDepartment(int id);

    /**
     * 通过id获取一个部门
     * @param id
     * @return
     */
    Department getDepartment(int id);

    /**
     * 更新一个部门的数据
     * @param department
     */
    void updateDepartment(Department department);

    /**
     * 获取所有的部门
     * @return
     */
    List<Department> getAll();
}
