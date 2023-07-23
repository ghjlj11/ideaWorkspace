package com.ghj.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author guohuanjun1
 * @date 2023/7/23 16:38
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MyDept {
    private Long id;
    private String name;
    private String address;
}
