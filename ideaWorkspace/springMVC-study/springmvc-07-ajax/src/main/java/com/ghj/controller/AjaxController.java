package com.ghj.controller;

import com.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */

@RestController
public class AjaxController {

    @RequestMapping("/aa")
    public void test(String name, HttpServletResponse response) throws IOException {
        System.out.println("name=>" + name);
        if("ghj".equals(name)){
            response.getWriter().print("true");
        }
        else {
            response.getWriter().print("false");
        };
    }

    @RequestMapping("/a2")
    public List<User> test2(){
        List<User> lists = new ArrayList<>();
        lists.add(new User("郭欢军1号",21,"男"));
        lists.add(new User("郭欢军2号",22,"女"));
        lists.add(new User("郭欢军2号",23,"男"));
        for (User list : lists) {
            System.out.println(list);
        }
        return lists;
    }

    @RequestMapping("/a3")
    public String test3(String name, String psw){
        String msg = "";
        if(name != null){
            if("lj".equals(name)){
                msg = "ok";
            }
            else {
                msg = "用户不存在";
            }
        }
        if(psw != null){
            if("12345".equals(psw)){
                msg = "ok";
            }
            else {
                msg = "密码错误";
            }
        }
        return msg;
    }
}
