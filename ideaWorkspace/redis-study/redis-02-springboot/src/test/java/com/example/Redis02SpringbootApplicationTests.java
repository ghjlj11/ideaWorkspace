package com.example;

import com.example.pojo.MyUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() throws JsonProcessingException {
        MyUser myUser = new MyUser("郭欢军", 2);
        redisTemplate.opsForValue().set("me", myUser);
        System.out.println(redisTemplate.opsForValue().get("me"));
    }

}
