package work;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/17 11:50
 */
public class Test1217 {

    private static AtomicInteger codeNum;

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowDate = dateFormat.format(new Date());
        String s = nowDate + "001";
        try {
            codeNum = new AtomicInteger(Integer.parseInt(s.substring(8)));
        } catch (Exception e) {
            codeNum = new AtomicInteger(0);
        }
    }
    public static void main(String[] args) {
        String s = "001";
        //String[] s1 = s.split("_");
        System.out.println(Arrays.toString(s.split(",")));
        System.out.println(codeNum.get());
        System.out.println(new Date().toString());
    }
}
