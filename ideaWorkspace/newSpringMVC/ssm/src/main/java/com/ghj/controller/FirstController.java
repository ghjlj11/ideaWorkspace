package com.ghj.controller;

import com.ghj.mapper.UserMapper;
import com.ghj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86187
 */
@RestController
public class FirstController {

    @Autowired
    UserMapper userMapper;

    /**
     * 通过ResponseEntity.BodyBuilder来设置响应头的content-type，还有charset，就没有中文？？了
     * @param id
     * @return
     */
    @RequestMapping("/select")
    public ResponseEntity<String> select(int id){
        User user = userMapper.selectById(id);
        System.out.println(user.toString());
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.OK);
        ResponseEntity<String> body = bodyBuilder.header("content-type", "application/json;charset=UTf-8")
                .body(user.toString());
        return body;
//        return "hello";
    }
}
