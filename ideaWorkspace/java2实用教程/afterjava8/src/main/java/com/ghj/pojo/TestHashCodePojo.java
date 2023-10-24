package com.ghj.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/23 16:36
 */
@Data
public class TestHashCodePojo {

    @NotBlank(message = "name not null")
    private String name;
    private int age;
}
