package com.ghj.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Data
@Component
public class Hello {
    @Value("lj")
    private String hello2;
    @Value("#{2}")
    private int k;
}
