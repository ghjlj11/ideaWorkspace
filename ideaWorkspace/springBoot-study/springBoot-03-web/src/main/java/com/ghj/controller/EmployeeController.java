package com.ghj.controller;

import com.ghj.dao.EmployeeDao;
import com.ghj.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author 86187
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @RequestMapping("/emps")
    public String show(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emp", employeeDao);
        return "/list";
    }
}
