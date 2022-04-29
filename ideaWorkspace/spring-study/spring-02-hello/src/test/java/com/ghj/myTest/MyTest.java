package com.ghj.myTest;

import com.ghj.pojo.Hello;
import com.ghj.pojo.HelloT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Hello hello = (Hello) context.getBean("hello");
        Hello hello1 = (Hello) context.getBean("hello2");
        HelloT helloT = (HelloT) context.getBean("u8");
        System.out.println(helloT);
        System.out.println(hello == hello1);
        System.out.println(hello);
    }
}
