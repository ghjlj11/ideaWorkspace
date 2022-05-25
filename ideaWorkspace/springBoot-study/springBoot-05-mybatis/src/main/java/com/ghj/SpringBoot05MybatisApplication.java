package com.ghj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 86187
 */
@SpringBootApplication(scanBasePackages = {"com.ghj"})
@MapperScan("com.ghj.mapper")
public class SpringBoot05MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot05MybatisApplication.class, args);
    }

}
