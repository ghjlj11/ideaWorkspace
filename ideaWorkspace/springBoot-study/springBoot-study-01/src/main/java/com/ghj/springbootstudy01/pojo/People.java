package com.ghj.springbootstudy01.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */

@Component
@Configuration
@ConfigurationProperties(prefix = "people")
@Data
public class People {
    private String name;
    private int age;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;
}
