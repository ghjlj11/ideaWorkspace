package com.ghj.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Component
public class User {
    //@Value("kk")
    public String name;

    @Value("lj")
    public void setName(String name) {
        this.name = name;
    }
}
