package test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Random;

/**
 * @author 86187
 */
public class Test6 {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        long l = date.getTime();
        Random random = new Random();
        Date date1 = new Date(l + random.nextInt());
        System.out.println(date1);
        long l1 = date1.getTime();
        long road = 1000 * 60 * 60 * 24;
        System.out.println((l1 - l)/road);
    }
}
