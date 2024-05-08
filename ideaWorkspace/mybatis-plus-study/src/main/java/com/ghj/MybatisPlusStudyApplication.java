package com.ghj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author ghj
 */
@SpringBootApplication
public class MybatisPlusStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusStudyApplication.class, args);
    }

}
