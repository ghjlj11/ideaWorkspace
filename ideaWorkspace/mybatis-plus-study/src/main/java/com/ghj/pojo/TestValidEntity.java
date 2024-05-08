package com.ghj.pojo;

import com.ghj.service.PeopleService;
import com.ghj.valid.Group;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/26 17:29
 */
@Data
@Accessors(chain = true)
@Validated
public class TestValidEntity implements Serializable {

    @NotBlank(message = "name 不可为空", groups = Group.Group1.class)
    private String name;

    @Max(value = 50, message = "age 不可以大于50", groups = {Group.Group1.class,  Group.Group2.class})
    @NotNull
    private Integer age;


}
