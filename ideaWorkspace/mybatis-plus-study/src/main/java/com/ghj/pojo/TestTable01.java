package com.ghj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/24 23:42
 */

@TableName("test01")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestTable01 {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField("name")
    private String name;
}
