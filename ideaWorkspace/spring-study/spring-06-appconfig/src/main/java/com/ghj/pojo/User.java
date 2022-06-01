package com.ghj.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
//@Component
public class  User {
    private String name;
    private Cat cat;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", cat=" + cat +
                '}';
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public User() {
    }

    @Value("ghj")
    public void setName(String name) {
        this.name = name;
    }

}
