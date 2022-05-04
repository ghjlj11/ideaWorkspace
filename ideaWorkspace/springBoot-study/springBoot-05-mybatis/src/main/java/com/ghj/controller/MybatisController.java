package com.ghj.controller;

import com.ghj.pojo.User;
import com.ghj.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 86187
 */

@RestController
public class MybatisController {

    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/add")
    public void add(){
        userService.add(new User(4, "郭欢军", 21));
    }
    @RequestMapping("/selectAll")
    public List<User> selectAll(){
        return userService.selectAll();
    }
    @RequestMapping("/selectById/{id}")
    public User selectById(@PathVariable("id") int id){
        return userService.selectById(id);
    }
    @RequestMapping("/update")
    public void update(){
        userService.update(new User(3456789, "郭欢军", 21));
    }
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        userService.delete(id);
    }
}
