package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Map;

/**
 * @author 86187
 */
@Alias("department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private int id;
    private String name;

}
