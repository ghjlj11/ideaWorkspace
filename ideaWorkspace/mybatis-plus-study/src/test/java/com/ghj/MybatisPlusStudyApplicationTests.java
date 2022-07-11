package com.ghj;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ghj.mapper.UserMapper;
import com.ghj.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusStudyApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);

        System.out.println("结果输出!");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void insert(){
        User user = new User();
        user.setName("ghj");
        user.setAge(21);
        user.setEmail("123456");

        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId(1546474832920682500L);
        user.setName("ss");
        user.setAge(21);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

}
