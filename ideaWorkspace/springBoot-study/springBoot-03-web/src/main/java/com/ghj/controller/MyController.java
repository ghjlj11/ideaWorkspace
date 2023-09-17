package com.ghj.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    @ResponseBody
    @RequestMapping("/test")
    public void test04(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + "excelTest07.xlsx" );
        String path = "D:\\my-study\\ideaWorkspace\\test\\excelTest07.xlsx";
        InputStream inputStream = new FileInputStream(path);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0){
            outputStream.write(bytes, 0 , len);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
