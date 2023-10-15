package work;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    }
}
