package com.ghj.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */
@Alias("teacher")
@Data
public class Teacher {
    private int id;
    private String name;
}
