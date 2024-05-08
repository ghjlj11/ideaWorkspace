package com.ghj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/4/25 22:21
 */
@Data
@Accessors(chain = true)
@TableName("people")
@AllArgsConstructor
@NoArgsConstructor
public class People {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

    @TableField("sex")
    private Sex sex;

    @TableField("brith")
    private LocalDateTime brith;

    @TableField("height")
    private BigDecimal height;

    @TableField("weight")
    private BigDecimal weight;
}
