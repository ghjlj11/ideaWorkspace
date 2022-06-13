package com.ghj;

import com.ghj.pojo.Department;
import com.ghj.service.DepartmentService;
import com.ghj.service.EmployeeService;
import com.ghj.service.Impl.DepartmentServiceImpl;
import com.ghj.service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot03WebApplicationTests {

    @Autowired
    DepartmentServiceImpl departmentService ;
    @Autowired
    EmployeeService employeeService;

    @Test
    void contextLoads() {


        System.out.println(employeeService.getAll());
    }

}
