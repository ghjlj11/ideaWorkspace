package com.ghj.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author 86187
 */
public class People {
    @Autowired(required = true)
    private Dog dog;
    @Autowired(required = true)
    private Cat cat;

    @Override
    public String toString() {
        return "People{" +
                "dog=" + dog +
                ", cat=" + cat +
                '}';
    }

    public Dog getDog() {
        return dog;
    }

    public Cat getCat() {
        return cat;
    }

}
