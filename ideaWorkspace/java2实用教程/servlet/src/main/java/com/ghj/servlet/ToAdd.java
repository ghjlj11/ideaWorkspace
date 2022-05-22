package com.ghj.servlet;

import com.ghj.dao.ClazzDao;
import com.ghj.pojo.Clazz;
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
@WebServlet("/toAdd")
public class ToAdd extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClazzDao clazzDao = new ClazzDao();
        try {
            List<Clazz> list = clazzDao.selectAll();
            req.setAttribute("clazz",list);
            req.getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
