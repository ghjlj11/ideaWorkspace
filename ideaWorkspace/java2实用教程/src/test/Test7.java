package test;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * @author 86187
 */
public class Test7 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        Date d1 = new Date();
        c.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("公元 yyyy年MM月dd日 HH时mm分ss秒");
        System.out.println(simpleDateFormat.format(d1));
    }
}
