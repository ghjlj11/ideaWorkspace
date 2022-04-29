package dateBefore8;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Calendar calendar = Calendar.getInstance();

        Date[] dates = new Date[10];

        for(int i = 0 ; i < dates.length ; i ++){
            long l = calendar.getTimeInMillis() + random.nextInt();
            dates[i] = new Date(l);
        }
        DateSort.dateSort(dates);
        System.out.println(Arrays.toString(dates));
    }
}
