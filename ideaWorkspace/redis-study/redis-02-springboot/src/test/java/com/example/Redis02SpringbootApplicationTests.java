package com.example;

import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.example.pojo.MyUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
            String[] strings = {"1", "2"};
            MyUser me = (MyUser)redisTemplate.opsForValue().get("me");
            System.out.println(me);
        }

    }

    @Test
    void testYonYou(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //threadPoolTaskExecutor.submit();
        ThreadPoolExecutor threadPoolExecutor = threadPoolTaskExecutor.getThreadPoolExecutor();

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

    @Test
    void testThreadPoolTaskExecutor() throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        Future<?> haha = threadPoolTaskExecutor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("haha");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        haha.get();
    }
}
