package com.ghj.test;

import com.ghj.pojo.Hello;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class T1 {
    @Autowired
    public Hello hello;
}