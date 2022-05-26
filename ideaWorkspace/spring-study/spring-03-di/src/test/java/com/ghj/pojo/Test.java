package com.ghj.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
        Properties p = student.getInfo();
        System.out.println(p.get("url"));
    }

    @org.junit.Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student", Student.class);
        List<String> hobby = student.getHobby();
        System.out.println(hobby.getClass());
//        User user1 = context.getBean("user",User.class);
//        User user = context.getBean("user", User.class);
//        System.out.println(user1 == user);
//        System.out.println(user1);
    }
}
