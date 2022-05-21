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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author 86187
 */

@WebServlet("/selectAll")
public class MyQuery extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StudentService service = new StudentService();
            ClazzDao clazzDao = new ClazzDao();
            List<Clazz> list = clazzDao.selectAll();
            HashMap<Integer, Clazz> map  = new HashMap<>();
            for (Clazz clazz : list) {
                map.put(clazz.getId(), clazz);
            }
            List<Student> aLl = service.getAll();
            req.setAttribute("students",aLl);
            req.setAttribute("clazz", map);
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
