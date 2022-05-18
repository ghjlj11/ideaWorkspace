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
        Connection connection = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
        Statement statement = connection.createStatement();
        String SQL = "select * from user where id = 2";
        ResultSet resultSet = statement.executeQuery(SQL);
        while (resultSet.next()){
            String name = resultSet.getString("name");
            System.out.println(name);
        }
        System.out.println(-5/2);
        statement.close();
        connection.close();
    }
}
