package com.ghj.servlet;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghj.pojo.Student;
import com.ghj.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
@WebServlet("/delete")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService service = new StudentService();
        try {
            System.out.println(req.getParameter("id"));
            service.delete(Integer.parseInt(req.getParameter("id")));
//            List<Student> all = service.getAll();
//            Map<String, Object> map  = new HashMap<>();
//            map.put("data", all);
//            System.out.println(map);
//            String s = JSON.toJSONString(map);
//            System.out.println(s);
//            resp.getWriter().println(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
