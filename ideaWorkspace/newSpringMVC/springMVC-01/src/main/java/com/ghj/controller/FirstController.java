package com.ghj.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author 86187
 */
@RestController
public class FirstController {
    @GetMapping("/first")
    public ResponseEntity<String> add(){
        String body = "qwerty";
        HttpHeaders head = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<String> response = new ResponseEntity<String>(body, head, status);
        return response;
    }
    @PostMapping("/first")
    public String add1(){
        return "Post#add";
    }
    @PutMapping("/first")
    public String add2(){

        return "Put#add";
    }
}
