package com.ghj.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ghj
 */

@TableName("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 表的主键注解
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("email")
    private String email;

    /**
     * 自动填充功能， fill
     */
    @TableField(fill = FieldFill.INSERT )
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 乐观锁注解
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除注解
     */
    @TableLogic
    private Integer deleted;

}
