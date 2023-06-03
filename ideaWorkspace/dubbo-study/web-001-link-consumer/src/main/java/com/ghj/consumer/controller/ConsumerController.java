package com.ghj.consumer.controller;


import com.ghj.api.entity.User;
import com.ghj.api.service.UserProviderService;
//import com.ghj.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @author 86187
 */
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private UserProviderService userProviderService;


    @RequestMapping("/getUser")
    public String getUser(Model model){

        List<User> hello =  userProviderService.getUsers();

        model.addAttribute("hello", hello.toString());
        return "hello";
    }
}
