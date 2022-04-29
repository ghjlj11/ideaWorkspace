package com.ghj.config;

import com.ghj.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 86187
 * 使用配置类两种方式获得User实例：1.配置类加@Bean就可以 ； 2.扫描包（@ComponentScan）+ @Component；
 * 1. @Bean就相当于在Spring里面写了一个Bean；
 * 2. 就相当于是05模块里的内容，在xml文件中写了scan，直接扫描包；
 */
@Configuration
@Import(GhjConfig2.class)
@ComponentScan("com.ghj.pojo")
public class GhjConfig {

    @Bean("user")
    public User getUser(){
        return new User();
    }
}
