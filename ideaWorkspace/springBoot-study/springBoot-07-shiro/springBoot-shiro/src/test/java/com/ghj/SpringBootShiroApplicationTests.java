package com.ghj;

import com.ghj.pojo.People;
import com.ghj.service.PeopleService;
import com.ghj.service.PeopleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootShiroApplicationTests {

    @Autowired
    PeopleServiceImpl service;
    @Test
    void contextLoads() {
        List<People> people = service.selectAll();
        System.out.println(people);
    }

}
