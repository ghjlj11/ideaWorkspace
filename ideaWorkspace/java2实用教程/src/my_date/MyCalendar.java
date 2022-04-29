package my_date;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 86187
 */
public class MyCalendar {
    public static void main(String[] args){

        Calendar calendar =Calendar.getInstance();
        calendar.set(2003, Calendar.MARCH, 21);
        calendar.set(Calendar.ERA, 1);

        Calendar.Builder builder = new Calendar.Builder();
        builder.setDate(2005, Calendar.JULY, 23);

        Calendar calendar1 = builder.build();
        System.out.println(calendar1);

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DATE_FIELD,DateFormat.DATE_FIELD);
        Date date = calendar.getTime();
        String f = dateFormat.format(date);
        long l = System.currentTimeMillis();
        long x = l/365/24/60/60/1000;
        System.out.println(x);
        System.out.println(calendar);
        System.out.println(date);
        System.out.println(f);
    }
}
