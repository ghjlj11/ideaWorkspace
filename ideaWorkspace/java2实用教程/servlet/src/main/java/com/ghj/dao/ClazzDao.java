package com.ghj.dao;

import com.ghj.pojo.Clazz;
import com.ghj.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class ClazzDao {
    public Clazz selectById(int id) throws SQLException{
        String SQL = "select * from clazz where id = ?";
        ResultSet resultSet = JdbcUtil.executeSelect(SQL, id);
        Clazz clazz = new Clazz();
        while (resultSet.next()){
            clazz.setId(resultSet.getInt("id"));
            clazz.setName(resultSet.getString("name"));
            clazz.setNo(resultSet.getString("no"));
        }
        return clazz;
    }
    public int add(Clazz clazz) throws SQLException {
        String SQL = "insert into clazz (id, name, no) values (?, ?, ?)";
        return JdbcUtil.executeUpdate(SQL, clazz.getId(), clazz.getName(), clazz.getNo());
    }
    public int delete (int id) throws  SQLException {
        String SQL = "delete from clazz where id = ?";
        return JdbcUtil.executeUpdate(SQL, id);
    }
    public int update(Clazz clazz) throws  SQLException {
        String SQL = "update clazz set name = ? , no = ? where id = ?";
        return JdbcUtil.executeUpdate(SQL, clazz.getName(), clazz.getNo(), clazz.getId());
    }
    public List<Clazz> selectAll() throws SQLException{
        String SQL = "select * from clazz";
        ResultSet resultSet =  JdbcUtil.executeSelect(SQL);
        List<Clazz> list = new ArrayList<>();
        while (resultSet.next()){
            Clazz clazz = new Clazz();
            clazz.setNo(resultSet.getString("no"));
            clazz.setId(resultSet.getInt("id"));
            clazz.setName(resultSet.getString("name"));
            list.add(clazz);
        }
        return list;
    }
}
