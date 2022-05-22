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
import java.util.List;

/**
 * @author 86187
 */
@WebServlet("/toModify")
public class ToModify extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        int i = Integer.parseInt(id);
        System.out.println(i);
        StudentService service = new StudentService();
        ClazzDao clazzDao = new ClazzDao();
        try {
            List<Clazz> list = clazzDao.selectAll();
            Student byId = service.getById(i);
            req.setAttribute("student", byId);
            req.setAttribute("clazz", list);
            System.out.println("-------------------------");
            req.getRequestDispatcher("/jsp/modify.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
