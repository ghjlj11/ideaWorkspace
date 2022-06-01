package com.ghj.config;

import com.ghj.pojo.Cat;
import com.ghj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 86187
 * 使用配置类两种方式获得User实例：1.配置类加@Bean就可以 ； 2.扫描包（@ComponentScan）+ @Component；
 * 1. @Configuration就相当于一个Beans， @Bean就相当于在Spring里面写了一个Bean， 获得这个Bean需要这个方法名， 就是getUser；
 * 2. 就相当于是05模块里的内容，在xml文件中写了scan，直接扫描包；
 */

//@Import(GhjConfig2.class)
//@ComponentScan("com.ghj.pojo")
@Configuration
public class GhjConfig {

    @Bean
    @Autowired
    public User getUser(Cat cat){
        User user =  new User();
        user.setCat(cat);
        return user;
    }
    @Bean
    public Cat getCat(){
        return new Cat();
    }
}
