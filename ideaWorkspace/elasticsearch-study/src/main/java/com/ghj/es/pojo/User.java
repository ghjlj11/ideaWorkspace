package com.ghj.es.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86187
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String sex;
    private int age;
}
