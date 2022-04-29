package com.ghj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghj.pojo.User;
import com.ghj.utils.JsonUtil;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 86187
 */

@RestController
public class Controller01 {

    @RequestMapping("/ll/jj")
    public String test1() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user  = new User("郭欢军", 123456, 21);
        return objectMapper.writeValueAsString(user);
    }

    @RequestMapping("/hh/jj")
    public String test2() throws JsonProcessingException {

        User user1  = new User("郭欢军", 123456, 21);
        User user2  = new User("郭军", 1234, 2);
        User user3  = new User("郭欢", 1256, 1);
        User user4  = new User("郭", 1456, 211);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        String str = JSON.toJSONString(userList);

        System.out.println("*******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(userList);
        System.out.println("JSON.toJSONString(list)==>"+str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println("JSON.toJSONString(user1)==>"+str2);

        System.out.println("\n****** JSON字符串 转 Java对象*******");
        User jp_user1=JSON.parseObject(str2,User.class);
        System.out.println("JSON.parseObject(str2,User.class)==>"+jp_user1);

        System.out.println("\n****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>"+jsonObject1.getString("name"));

        System.out.println("\n****** JSON对象 转 Java对象 ******");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println("JSON.toJavaObject(jsonObject1, User.class)==>"+to_java_user);
        return str;
    }
    @RequestMapping("/ll/hh")
    public String test3() throws JsonProcessingException {

        return JsonUtil.getJson(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
}
