package dateBefore8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author 86187
 */
public class DateSort {
    public static void dateSort(Date[] dates){
        Arrays.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                int res = 0;
                if(o1.before(o2)){
                    res = -1;
                }
                else if(o1.after(o2)){
                    res = 1;
                }
                return res;
            }
        });
    }
}
