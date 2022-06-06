package com.ghj.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Component
@Data
public class Test01 {
    @Value("#{5+5}")
    private int v1;
    @Value("2")
    private int v2;
    @Value("${hello}")
    private String hello;
    @Value("${hello2:hello2}")
    private String hello2;
}
