package com.ghj.springbootstudy01.pojo;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author 86187
 */

@Component
@ConfigurationProperties(prefix = "dog")
@Validated
public class Dog {
    @Email(message = "格式错误")
    private String dogName;
    private int age;

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog() {
    }

    public Dog(String dogName, int age) {
        this.dogName = dogName;
        this.age = age;
    }
}
