package work;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/13 14:46
 */
public class TestDate {
    public static void main(String[] args) {
        String value1 = "2023-10-13 14:16:22";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(value1, dateTimeFormatter);
        System.out.println(parse.getMonth().getValue());
        String s1 = "123";
        String s2 = new String("123");

        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));

        String s3 = "333";
        String s4 = "444";
        String s = "333444";
        String s5 = "333" + "444";
        String s6 = s3 + s4;
        System.out.println(s == s5);
        System.out.println(s == s6);
        // System.out.println(s == s7);
        String tt = new StringBuilder("ja").append("va").toString();
        System.out.println("k,k1==========>");
        System.out.println(tt.intern() == tt);
        System.out.println(s == s6.intern());
        int i = 0;
        Set<String> stringSet = new HashSet<>();
        while (true) {
            stringSet.add(String.valueOf(i ++).intern());
            System.out.println(i);
        }
    }
}
