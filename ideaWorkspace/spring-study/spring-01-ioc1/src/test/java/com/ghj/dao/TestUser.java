package com.ghj.dao;


import com.ghj.service.ServiceUserImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ServiceUserImpl serviceUserImpl = (ServiceUserImpl) context.getBean("ServiceUserImpl");
        serviceUserImpl.getUser();
    }
}
