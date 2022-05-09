package myjdbc;


import java.sql.*;
import java.util.Properties;

/**
 * @author 86187
 */
public class MyJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/malajava?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        Properties properties = new Properties();
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String SQl = "select id, name from people;";
        ResultSet resultSet = statement.executeQuery(SQl);

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println("id :" + id);
            System.out.println("name :" + name);
        }

        connection.close();
    }
}
