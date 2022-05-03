package com.ghj.springboot04data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */
@RestController
public class DataController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/que")
    public String select(){
        String s = jdbcTemplate.queryForList("select * from user").toString();
        return s;
    }
    @GetMapping("/add")
    public String add(){
        String sql = "insert into user(id, name, age) values(4, 'ghj', 12)";
        jdbcTemplate.update(sql);
        return "ok";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")int id){
        String sql = "update user set name = ?, age = ? where id = "+ id;
        Object[] objects = new Object[2];
        objects[0] = "kkk";
        objects[1] = 21;
        jdbcTemplate.update(sql,objects);
        return "ok";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id){
        String sql = "delete from user where id = ?";
        Object j = new Integer(4);
        jdbcTemplate.update(sql,j);
        return "ok";
    }
}
