package com.ghj.service.Impl;

import com.ghj.mapper.EmployeeMapper;
import com.ghj.pojo.Employee;
import com.ghj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 86187
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeMapper.getEmployee(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }
}
