package com.ghj.utils;

/**
 * @author 86187
 */
public class MyJDBCException extends RuntimeException{
    public MyJDBCException() {
        super();
    }

    public MyJDBCException(String message) {
        System.out.println(message);
    }

    public MyJDBCException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyJDBCException(Throwable cause) {
        super(cause);
    }

    protected MyJDBCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
