package com.ghj.servlet;

import com.alibaba.fastjson.JSON;
import com.ghj.dao.ClazzDao;
import com.ghj.pojo.Clazz;
import com.ghj.pojo.Student;
import com.ghj.service.StudentService;
import com.google.gson.Gson;

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
            HashMap<String, Object> res = new HashMap<>();
            res.put("students", aLl);
            res.put("clazz", map);
            Gson gson = new Gson();
            String s = gson.toJson(res);
            resp.getWriter().println(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
