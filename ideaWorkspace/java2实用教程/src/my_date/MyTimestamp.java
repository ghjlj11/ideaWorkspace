package my_date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 86187
 */
public class MyTimestamp {
    public static void main(String[] args) throws ParseException {
        long l = System.currentTimeMillis();
        Timestamp timestamp  = new Timestamp(l);
        Date time = new Date(timestamp.getTime()) ;

        DateFormat dateFormat = new SimpleDateFormat();
        String format = dateFormat.format(time);
        Date parse = dateFormat.parse(format);
        Calendar.Builder builder = new Calendar.Builder();
        Calendar calendar = builder.build();

        System.out.println(format);
        System.out.println(timestamp);
        System.out.println(time);
        ArrayList<String> a = new ArrayList<>();
        int[][] nums = {{4,5},{3,4},{2,3}};
        Arrays.sort(nums,  new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(nums, (o1, o2) -> o1[0] - o2[0]);
        int[] p = {1,2,3,4,5,6,9,8,7};
        Arrays.sort(p);
        for(int i = 0 ; i < 3 ; i ++){
            System.out.println(nums[i][0]);
        }
    }
}
