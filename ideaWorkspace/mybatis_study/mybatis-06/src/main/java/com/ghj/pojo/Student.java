package com.ghj.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */
@Alias("student")
@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
