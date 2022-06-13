package com.ghj;

import com.ghj.pojo.People;
import com.ghj.service.PeopleService;
import com.ghj.service.PeopleServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class SpringBootShiroApplicationTests {

    @Autowired
    PeopleServiceImpl service;
    @Test
    void contextLoads() {
//        List<People> people = service.selectAll();
//        System.out.println(people);
        //ac29b283569fd6bdb9c481e684985b4c
        //md5加盐加密。
        String s = "14123456789";
        System.out.println("md5加密===>" + new Md5Hash(s , "ghj"));

        String pattern = "^1[3-9][0-9]{9}";
        System.out.println(Pattern.matches("^1[3-9][0-9]{9}",s));

        StringBuffer k = new StringBuffer("123456");
        while (true){
            k = k.append(k);
            System.out.println("======>" + k.length());
        }
    }

}
