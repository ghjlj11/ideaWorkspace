package workTest;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 86187
 */
public class TestBigDecimal {
    public static void main(String[] args) {
        String s = "12345678987653312234356";
        BigDecimal bigDecimal = new BigDecimal(s);
        System.out.println(bigDecimal.add(new BigDecimal("123")));
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("1", "1");
        stringObjectHashMap.remove("2");
        String s1 = "qwertwtyu";
        System.out.println(s1.lastIndexOf("p"));
        System.out.println(s1.substring(0, 3));
        System.out.println(s1);
        BigDecimal bigDecimal1 = new BigDecimal("12.44");
        BigDecimal bigDecimal2 = new BigDecimal("3.12");
        System.out.println(bigDecimal1.divide(bigDecimal2, 0, RoundingMode.UP));

        String s2 = "qweqe";
        String[] split = s2.split(":");
        System.out.println(Arrays.toString(split));
        String k = "";
        String k1 = null;
        String k2 = null;

        Boolean o = null;
        k  = k1 + k2;
        if(o){
            System.out.println("12345");
        }
        System.out.println(k);
    }
}
