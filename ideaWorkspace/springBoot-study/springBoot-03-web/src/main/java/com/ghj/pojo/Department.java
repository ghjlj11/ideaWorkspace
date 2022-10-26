package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 86187
 */
@Alias("department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Department {
    private int id;
    private String name;

}
