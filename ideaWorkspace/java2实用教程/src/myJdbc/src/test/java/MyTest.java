import com.mysql.cj.xdevapi.JsonArray;
import org.junit.Test;
import utils.JdbcUtil;

import java.io.Reader;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class MyTest {

    @Test
    public void test1() throws SQLException {

        String SQL = "select * from user";
        Integer id = 2;
        ResultSet resultSet = JdbcUtil.executeSelect(SQL);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("age"));
        }

    }
    @Test
    public void test2() throws SQLException {

        String SQL = "insert into user (id, name, age) values(?, ? ,?)";
        int kk = JdbcUtil.executeUpdate(SQL, 5, "kk", 23);
        System.out.println(kk);


    }
    @Test
    public void test3() throws SQLException {

        String SQL = "update user set name = ?, age = ? where id = ?";
        int kk = JdbcUtil.executeUpdate(SQL, "lk", 12, 5);
        System.out.println(kk);

    }
    @Test
    public void test4() throws SQLException {

        String SQL = "delete from user where id = ?";
        int kk = JdbcUtil.executeUpdate(SQL,  5);
        System.out.println(kk);

    }
}
