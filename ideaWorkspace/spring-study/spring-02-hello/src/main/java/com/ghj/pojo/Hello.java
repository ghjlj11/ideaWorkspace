package com.ghj.pojo;

/**
 * @author 86187
 */
public class Hello {
    private String name;

    private int age;
    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                "age='" + age + '\'' +
                '}';
    }

    public Hello(String name , int age) {
        this.age = age;
        this.name = name;
        System.out.println("Hello的有参构造");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Hello() {
        System.out.println("Hello的无参构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
