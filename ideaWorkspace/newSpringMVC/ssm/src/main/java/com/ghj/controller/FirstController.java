package com.ghj.controller;

import com.ghj.mapper.UserMapper;
import com.ghj.model.User;
import com.ghj.service.UserServiceImpl;
import com.ghj.util.MyException;
import com.ghj.util.MyResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 86187
 */
@Controller
public class FirstController {

    @Autowired
    UserServiceImpl userMapper;

    /**
     * 通过ResponseEntity.BodyBuilder来设置响应头的content-type，还有charset，就没有中文？？了
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/select")
    public ResponseEntity<User> select(int id){
        User user = userMapper.selectById(id);
        System.out.println(user.toString());
        //MyException.creatException();
        Set<String> set = new HashSet<>();
        ResponseEntity response = MyResponseEntity.getResponseEntity(user, HttpStatus.OK);
        return response;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
