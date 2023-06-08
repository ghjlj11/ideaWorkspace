package com.ghj.consumer.controller;


import com.ghj.api.service.UserProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author 86187
 */
@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    UserProviderService userProviderService;

    @Resource
    UserProviderService newUserProviderService;

    @RequestMapping("/getUser")
    public String getUser(Model model){

        model.addAttribute("hello", userProviderService.getUsers().toString());
        return "hello";
    }

    /**
     * 测试不同版本对应的接口实现类
     * @param model
     * @return
     */
    @RequestMapping("/getNewUser")
    public String getNewUser(Model model){

        model.addAttribute("hello", newUserProviderService.getUsers().toString());
        return "hello";
    }
}
