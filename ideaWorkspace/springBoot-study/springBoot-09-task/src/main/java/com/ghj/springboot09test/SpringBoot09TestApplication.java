package com.ghj.springboot09test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 86187
 */

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBoot09TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09TestApplication.class, args);
    }

}
