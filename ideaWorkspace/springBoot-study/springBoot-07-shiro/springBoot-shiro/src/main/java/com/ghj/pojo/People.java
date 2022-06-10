package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author 86187
 */

@Alias("people")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People implements Serializable {
    private int id;
    private String name;
    private String pwd;
    private String perms;
}
