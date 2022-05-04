package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("user")
public class User {
    private int id;
    private String name;
    private int age;
}
