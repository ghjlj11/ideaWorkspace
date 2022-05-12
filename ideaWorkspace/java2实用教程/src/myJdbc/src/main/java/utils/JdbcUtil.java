package utils;

import lombok.Data;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 86187
 */
@Data

public class JdbcUtil {
    public static final String FOR_NAME;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    static {
        Properties properties = new Properties();
        try {
            InputStream stream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

            Reader reader = new FileReader("D:/my-study/ideaWorkspace/java2实用教程/src/myJdbc/src/main/resources/jdbc.properties");
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FOR_NAME = properties.getProperty("driverClassName");
        URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");
    }
}
