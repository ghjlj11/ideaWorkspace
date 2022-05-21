package com.ghj.service;

import com.ghj.dao.MyDao;
import com.ghj.pojo.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 86187
 */
public class StudentService {
    MyDao myDao = new MyDao();

 
    public List<Student> getAll() throws SQLException{
        return myDao.selectAll();
    }
    public Student getById(int id) throws SQLException   {
        return myDao.selectById(id);
    }
    public int update(Student student) throws SQLException   {
        return myDao.update(student);
    }
    public int add(Student student) throws SQLException   {
        return myDao.add(student);
    }
    public int delete(int id) throws SQLException   {
        return myDao.delete(id);
    }
}
