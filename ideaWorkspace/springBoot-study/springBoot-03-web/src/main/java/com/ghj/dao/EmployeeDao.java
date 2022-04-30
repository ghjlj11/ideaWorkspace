package com.ghj.dao;

import com.ghj.pojo.Department;
import com.ghj.pojo.Employee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public class EmployeeDao {
    private static Map<Integer, Employee> employees = new HashMap<>();
    static {
        employees.put(1001, new Employee(1001, "小明", "12345", 1, DepartmentDao.departments.get(101)));
        employees.put(1002, new Employee(1002, "小红", "12345", 0, DepartmentDao.departments.get(102)));
        employees.put(1003, new Employee(1003, "小军", "12345", 1, DepartmentDao.departments.get(103)));
        employees.put(1004, new Employee(1004, "小丽", "12345", 0, DepartmentDao.departments.get(104)));
        employees.put(1005, new Employee(1005, "小亮", "12345", 1, DepartmentDao.departments.get(105)));
        employees.put(1006, new Employee(1006, "小芳", "12345", 0, DepartmentDao.departments.get(103)));
    }
    public void addEmployee(Employee employee){
        employees.put(employee.getId(), employee);
    }
    public void deleteEmployee(int id){
        employees.remove(id);
    }
    public Employee getEmployee(int id){
        return employees.get(id);
    }
    public void updateEmployee(Employee employee){
        if(employees.containsKey(employee.getId())){
            employees.put(employee.getId(), employee);
        }
        else {
            System.out.println("查无此人");
        }
    }
}
