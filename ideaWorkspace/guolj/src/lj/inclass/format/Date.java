package lj.inclass.format;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.lang.Math;
/**
 * @author 86187
 */
public class Date {
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int second = calendar.get(Calendar.SECOND);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        long time = calendar.getTimeInMillis();
        Random random = new Random();
        int rn = random.nextInt(4);
        NumberFormat numberFormat = new DecimalFormat("#.0000");
        numberFormat.setRoundingMode(RoundingMode.DOWN);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(second);
        System.out.println(calendar);
        System.out.println(time);
        System.out.println(rn);
    }
}
