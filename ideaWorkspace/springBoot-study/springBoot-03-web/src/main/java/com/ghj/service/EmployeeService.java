package com.ghj.service;

import com.ghj.pojo.Employee;

import java.util.List;

/**
 * 员工的操作
 * @author 86187
 */
public interface EmployeeService {
    /**
     * 添加一个员工
     * @param employee
     */
    void addEmployee(Employee employee);

    /**
     * 通过id删除一个员工
     * @param id
     */
    void deleteEmployee(int id);

    /**
     * 通过id获取一个员工
     * @param id
     * @return
     */
    Employee getEmployee(int id);

    /**
     * 修改一个员工
     * @param employee
     */
    void updateEmployee(Employee employee);

    /**
     * 获取所有的员工
     * @return
     */
    List<Employee> getAll();
}
