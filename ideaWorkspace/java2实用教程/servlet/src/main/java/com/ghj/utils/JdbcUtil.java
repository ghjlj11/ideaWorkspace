package com.ghj.utils;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author 86187
 */
@Data

public class JdbcUtil {
    private static final String FOR_NAME;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static  Connection con;
    private static final String SELECT = "select";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String ADD = "insert into";
    static {
        con = null;
        Properties properties = new Properties();
        try {
            InputStream stream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FOR_NAME = properties.getProperty("driverClassName");
        URL = properties.getProperty("url");
        USERNAME = properties.getProperty("username");
        PASSWORD = properties.getProperty("password");
        try {
            Class.forName(FOR_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *验证是否是SQL， 初步验证；
     * @param SQL
     * @return
     */
    public static ResultSet executeSelect(String SQL, Object... params) throws SQLException {
        ResultSet resultSet = null;
        isDQL(SQL);
        PreparedStatement statement = con.prepareStatement(SQL);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        resultSet = statement.executeQuery();

        return resultSet;
    }
    public static int executeUpdate(String SQL, Object... params) throws SQLException {
        int res = 0;
        isDML(SQL);
        PreparedStatement statement = con.prepareStatement(SQL);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        res = statement.executeUpdate();

        return res;
    }

    private static void isSQL(String SQL){
        if(SQL == null){
            throw new MyJDBCException("空的语句");
        }
        String s = SQL.toLowerCase();
        if( !s.startsWith(SELECT) && !s.startsWith(ADD) && !s.startsWith(DELETE) && !s.startsWith(UPDATE)){
            throw new MyJDBCException("SQL有问题");
        }
    }

    /**
     * 验证是否是DML
     * @param SQL
     * @return
     */
    private static void isDML(String SQL){
        isSQL(SQL);
        if(SQL.toLowerCase().startsWith(SELECT)){
            throw new MyJDBCException("调用错了");
        }
    }
    /**
     * 验证是否是DQL
     * @param SQL
     * @return
     */
    private static void isDQL(String SQL){
        isSQL(SQL);
        if(!SQL.toLowerCase().startsWith(SELECT)){
            throw new MyJDBCException("调用错了");
        }
    }
}
