package com.example;

import com.example.pojo.MyUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.Set;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        MyUser myUser = new MyUser("郭欢军", 2);
        //redisTemplate.opsForValue().set("me", myUser);
        ValueOperations opsForValue = redisTemplate.opsForValue();
        if(opsForValue.setIfAbsent("me", myUser, Duration.ofDays(30))){
            System.out.println(redisTemplate.opsForValue().get("me"));
        }

    }

    @Test
    void testYonYou(){
        String key = "guo*";
        Set keys = redisTemplate.keys(key);
        System.out.println("=======查找到：" + keys.size() + "个key");
        for (Object o : keys) {
            MyUser o1 = (MyUser) redisTemplate.opsForValue().get(o);
            System.out.println(o1);
        }
        Long delete = redisTemplate.delete(keys);
        System.out.println("=======删除了：" + delete + "个key");
//        redisTemplate.opsForValue().set("guoguo", new MyUser("ghj", 21));
    }

}
