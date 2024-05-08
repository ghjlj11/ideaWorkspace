package work;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        double a = 3102.66;
        double b = 28.99;
        double c = a + b;
        String s1 = getS(c);
        System.out.println(s1);
        String ss1 = new String("123456");
        String ss2 = new String("123456");
        System.out.println(ss1.hashCode());
        System.out.println(ss2.hashCode());
        System.out.println(ss1 == ss2);
        String ss3 = "123";
        String[] split = ss3.split("\\|");
        System.out.println(Arrays.toString(split));
        int d = 7 << 16;
        System.out.println("d = " + d);
        String ghj = "012.34.56.789";
        System.out.println(ghj.replaceAll("\\.", "_"));
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(2);
        System.out.println(list.stream().sorted().collect(Collectors.toList()));
        BigDecimal decimal = BigDecimal.ZERO;
        System.out.println("decimal:" + decimal);
        System.out.println("pattern:" + Pattern.matches("^\\d{1,30}(\\.\\d{1,30})?$", decimal.toString()));
        System.out.println("compare:" + decimal.compareTo(decimal));
        Map<String, Object> map = new HashMap<>();
        map.forEach( (k, v) -> {});
    }

    public static String getS (Object o) {
        return o == null ? "" : o.toString();
    }

    public static void test (Boolean b) {
        b = true;
    }
}
