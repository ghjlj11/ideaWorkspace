package my_date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author 86187
 */
public class MyDate {
    public static void main(String[] args){
        Date date = new Date();
        long l = System.currentTimeMillis();
        long x = l/365/24/60/60/1000;
        System.out.println(x);
        System.out.println(date);
    }
}
