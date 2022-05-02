package com.ghj.controller;

import com.ghj.dao.DepartmentDao;
import com.ghj.dao.EmployeeDao;
import com.ghj.pojo.Department;
import com.ghj.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author 86187
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String show(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        System.out.println(employees);
        return "/emp/list";
    }

    @GetMapping("/add")
    public String add(Model model){
        Collection<Department> all = departmentDao.getAll();
        model.addAttribute("all",all);
        return "/emp/addEmp";
    }
    @PostMapping("/add")
    public String add(Employee employee){
        employeeDao.addEmployee(employee);
        return "redirect:/emps";
    }
    @GetMapping("/update")
    public String update(Integer id, Model model){
        Collection<Department> all = departmentDao.getAll();
        model.addAttribute("all",all);
        Employee emp = employeeDao.getEmployee(id);
        System.out.println(emp);
        model.addAttribute("emp", emp);
        return "/emp/update";
    }
    @PostMapping("/update")
    public String update(Employee employee){
        employeeDao.updateEmployee(employee);
        return "redirect:/emps";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.deleteEmployee(id);
        return "redirect:/emps";
    }
}
