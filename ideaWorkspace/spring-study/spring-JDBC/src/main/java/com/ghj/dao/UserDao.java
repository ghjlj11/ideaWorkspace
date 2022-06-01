package com.ghj.dao;

import com.ghj.pojo.User;
import lombok.Data;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 86187
 */
@Data
public class UserDao {
    JdbcTemplate jdbcTemplate;
    public User selectById(int id){
        ResultSetExtractor<User> mapper = new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                int id1 = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new User(id1, name, age);
            }
        };
        String SQL = "select * from user where id = ?";
        return jdbcTemplate.query(SQL, mapper, id);
    }
}
