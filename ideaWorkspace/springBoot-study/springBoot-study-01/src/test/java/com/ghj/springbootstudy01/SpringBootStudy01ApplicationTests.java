package com.ghj.springbootstudy01;

import com.ghj.springbootstudy01.pojo.Dog;
import com.ghj.springbootstudy01.pojo.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootStudy01ApplicationTests {

    @Autowired
    private Dog dog;
    @Test
    void contextLoads() {
        System.out.println(dog);
    }

}
