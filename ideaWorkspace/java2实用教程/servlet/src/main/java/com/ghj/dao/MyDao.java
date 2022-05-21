package com.ghj.dao;

import com.ghj.pojo.Student;
import com.ghj.utils.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class MyDao {
    public List<Student> selectAll() throws SQLException {
        String SQL = "select * from student";
        ResultSet resultSet =   JdbcUtil.executeSelect(SQL);
        List<Student> list = new ArrayList<>();
        Student s ;
        while (resultSet.next()){
            s = new Student();
            s.setId(resultSet.getInt("id"));
            s.setUsername(resultSet.getString("username"));
            s.setNo(resultSet.getString("no"));
            s.setEmail(resultSet.getString("email"));
            s.setPhone(resultSet.getString("phone"));
            s.setClazz_id(resultSet.getInt("clazz_id"));
            s.setBirthdate(resultSet.getTimestamp("birthdate"));
            list.add(s);
        }
        return list;
    }
    public Student selectById(int id) throws   SQLException {
        String SQL = "select * from student where id = ?";
        ResultSet resultSet =   JdbcUtil.executeSelect(SQL,id);
        Student s = new Student();
        while (resultSet.next()){
            s.setId(resultSet.getInt("id"));
            s.setUsername(resultSet.getString("username"));
            s.setNo(resultSet.getString("no"));
            s.setEmail(resultSet.getString("email"));
            s.setPhone(resultSet.getString("phone"));
            s.setClazz_id(resultSet.getInt("clazz_id"));
            s.setBirthdate(resultSet.getTimestamp("birthdate"));
        }
        return s;
    }
    public int delete(int id) throws SQLException   {
        String SQL = "delete from student where id = ?";
        return JdbcUtil.executeUpdate(SQL, id);
    }
    public int add(Student s) throws SQLException  {
        String SQL = "insert into student(id, username, no, email, phone, birthdate,  clazz_id) values(? ,?, ?, ?, ? ,? ,?)";
        return JdbcUtil.executeUpdate(SQL, s.getId(), s.getUsername(), s.getNo(), s.getEmail(), s.getPhone(),s.getBirthdate(),  s.getClazz_id());
    }
    public int update(Student s) throws   SQLException {
        String SQL = "update student set username = ?, no = ?, email = ?, phone = ?, birthdate = ? , clazz_id = ? where id = ?";
        return JdbcUtil.executeUpdate(SQL, s.getUsername(), s.getNo(), s.getEmail(), s.getPhone(), s.getBirthdate()
        , s.getClazz_id(), s.getId());
    }
}
