package com.ghj.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 86187
 */
@WebServlet("/se")
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("name", "ghj");
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);
        System.out.println(servletContext);
        System.out.println(servletContext.getAttribute("name"));
    }
}
