package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 86187
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser{
    private String name;
    private Integer age;
}
