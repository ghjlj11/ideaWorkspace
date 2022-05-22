package com.ghj.servlet;

import com.ghj.dao.ClazzDao;
import com.ghj.pojo.Clazz;
import com.ghj.pojo.Student;
import com.ghj.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 86187
 */
@WebServlet("/add")
public class Add extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService service = new StudentService();
        Student student = new Student();
        student.setUsername(req.getParameter("username"));
        student.setNo(req.getParameter("no"));
        student.setEmail(req.getParameter("email"));
        student.setPhone(req.getParameter("phone"));
        student.setClazz_id(Integer.parseInt(req.getParameter("clazz_id")));
        System.out.println(req.getParameter("date"));
        String s = req.getParameter("date");
        LocalDateTime now = LocalDateTime.now();
        s = s + ' ' + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + "." + now.getNano() ;
        System.out.println(s);
        student.setBirthdate(Timestamp.valueOf(s));
        student.setUsername(req.getParameter("username"));
        try {
            service.add(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/main");
    }
}
