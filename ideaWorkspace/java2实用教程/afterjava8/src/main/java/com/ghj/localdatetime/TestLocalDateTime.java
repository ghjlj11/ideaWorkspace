package com.ghj.localdatetime;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 86187
 * java 8 之后的日期时间
 */
public class TestLocalDateTime {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date();
        System.out.println(localDate.plusDays(5).toString());
        System.out.println(localDateTime);
        System.out.println(date);
        String a = null;
        Long b = Long.valueOf(a);
        System.out.println(b);
    }
}
