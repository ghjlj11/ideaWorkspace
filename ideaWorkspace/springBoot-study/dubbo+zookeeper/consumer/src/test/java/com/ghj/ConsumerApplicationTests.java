package com.ghj;

import com.ghj.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ConsumerApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        userService.buy();
    }

}
