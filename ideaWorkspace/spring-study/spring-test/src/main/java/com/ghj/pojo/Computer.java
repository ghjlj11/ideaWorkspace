package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86187
 */
@Data
@AllArgsConstructor
public class Computer {
    private Integer id;
    private String name;
    private CPU cpu;
    static{
//        System.out.println("static....");
    }
    public void init (){
//        System.out.println("初始化....");
    }
    public void destroy(){
//        System.out.println("销毁了....");
    }
    public Computer(){
//        System.out.println("执行了 无参构造");
    }
}
