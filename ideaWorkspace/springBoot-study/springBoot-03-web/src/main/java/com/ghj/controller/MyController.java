package com.ghj.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 86187
 */
@Controller
public class MyController {
    @RequestMapping("/toLogin")
    public String test01(@RequestParam("username")String username, @RequestParam("password")String password , Model model, HttpSession session){
        if("ghjlj".equals(username) && "123456".equals(password)){
            session.setAttribute("Login", username);
            return "redirect:/main";
        }
        else {
            model.addAttribute("msg", "用户名或密码错误");
        }
        return "index";
    }

    @RequestMapping("/out")
    public String test02(HttpSession session){
        session.removeAttribute("Login");
        return "redirect:index";
    }

    @RequestMapping("/main")
    public String test03(Model model){
        return "dashboard";
    }
}
