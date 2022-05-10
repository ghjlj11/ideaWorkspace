package com.ghj.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 86187
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)//支持链式语法
public class Dept implements Serializable {//需要实现序列化， 这样才可以在网络里传输。
    private Long dept_no;
    private String d_name;
    private String db_source;

    /**
     * 只需要这个，其他的都自动获得
     */
    public Dept(String d_name){
        this.d_name = d_name;
    }
}
