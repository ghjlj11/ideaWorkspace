package com.ghj.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 86187
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        request.getRequestURL().indexOf("");
        StringBuffer url = request.getRequestURL();
        int len = url.length();
        Object login = session.getAttribute("Login");
        if(login == null && !url.substring(len - 4, len).equals("test")){
            request.setAttribute("msg", "未登录没有权限访问");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }
        return true;
    }
}
