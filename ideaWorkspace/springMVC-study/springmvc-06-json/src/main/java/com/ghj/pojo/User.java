package com.ghj.pojo;

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
    private long id;
    private int age;
}
