import com.mysql.cj.xdevapi.JsonArray;
import org.junit.Test;
import utils.JdbcUtil;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyTest {

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
        Statement statement = connection.createStatement();
        System.out.println(connection);
    }
}
