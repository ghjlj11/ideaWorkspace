package com.ghj.dao;

import com.ghj.pojo.Department;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
@Repository
public class DepartmentDao {
    static Map<Integer, Department> departments = new HashMap<>();

    static{
        departments.put(101,new Department(101, "开发部"));
        departments.put(102,new Department(102, "小卖部"));
        departments.put(103,new Department(103, "运维部"));
        departments.put(104,new Department(104, "测开部"));
        departments.put(105,new Department(105, "后勤部"));
    }
    public void addDepartment(Department department){
        departments.put(department.getId(), department);
    }
    public void deleteDepartment(int id){
        departments.remove(id);
    }
    public Department getDepartment(int id){
        return departments.get(id);
    }
    public void updateDepartment(Department department){
        if(departments.containsKey(department.getId())){
            departments.put(department.getId(), department);
        }
        else {
            System.out.println("查无此部门");
        }
    }
    public Collection<Department> getAll(){
        return departments.values();
    }
}
