package com.ghj.controller;

import com.ghj.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.Banner;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 86187
 */

@Controller
public class MyShiroController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, boolean rememberMe, Model model){
        Subject subject = SecurityUtils.getSubject();

        System.out.println("rememberMe===>" + rememberMe);
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.md5(password), rememberMe);
        System.out.println("token=====>" + token.getPrincipal());
        System.out.println("subject===>" + subject.getPrincipal() + "subject  : " + subject.getClass() );
        try {
            Session session = subject.getSession();
            session.setAttribute("isLogin", username);
            System.out.println("http=>"+session);
            System.out.println("subject=>"+subject.getSession());
            model.addAttribute("username",username);
            subject.login(token);
            return "hello";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            e.printStackTrace();
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    @RequestMapping({"/hello","/"})
    public String hello(Model model){
        model.addAttribute("msg","hello, shiro");
        return "hello";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @ResponseBody
    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "未授权，不允许访问";
    }

    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){

        session.removeAttribute("isLogin");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
