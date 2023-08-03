package com.ghj.testjson;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;


/**
 * @author guohuanjun1
 * @date 2023/7/24 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
}
