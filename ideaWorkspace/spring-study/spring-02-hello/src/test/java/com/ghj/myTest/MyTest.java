package com.ghj.myTest;

import com.ghj.pojo.Hello;
import com.ghj.pojo.HelloT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Hello hello = context.getBean("hello",Hello.class);
        Hello hello1 = context.getBean("hello2", Hello.class);
        HelloT helloT = (HelloT) context.getBean("u8");
        System.out.println(helloT);
        System.out.println(hello == hello1);
        System.out.println(hello);
    }
}
