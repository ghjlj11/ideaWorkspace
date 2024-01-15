package com.ghj.test.Exception;/**
 * <p>
 * Description:
 * <p>
 *      
 * @author guohuanjun1
 * @date 2024/1/15 21:42
 */public class MyException extends RuntimeException{
     private long code;
     private String msg;
     public MyException () {}

    public MyException(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg) {
        this.msg = msg;
    }
}
