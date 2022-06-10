package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
@Alias("employee")
@Data
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private String email;
    private int sex;
    private Department department;
    private Date brith;


    public Employee(int id, String name, String email, int sex, Department department){
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.department = department;
        this.brith = new Date();
    }
}
