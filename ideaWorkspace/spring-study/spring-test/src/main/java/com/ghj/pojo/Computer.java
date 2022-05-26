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
public class Computer {
    private Integer id;
    private String name;
    private CPU cpu;
}
