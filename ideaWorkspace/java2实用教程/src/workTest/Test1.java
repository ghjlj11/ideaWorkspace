package workTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public class Test1 {
    private int s;
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(3);
        Map<String, String> map2 = new HashMap<>(3);
        map.put("1", "1");
        map.put("2", "2");
        List<Map<String, String>> list = new ArrayList<>();
        List<Map<String, String>> list2 = new ArrayList<>();
        list.add(map);
        list2.add(map);
        list2.get(0).remove("1");
        System.out.println(list);
        T2 t2 = new T2();
        t2.t2();
        BigDecimal decimal = new BigDecimal(3);
        BigDecimal decimal1 = new BigDecimal(3);
        System.out.println(decimal.equals(decimal1));
        String s = new String("123");
        String s1 = new String("123");
        System.out.println(s == s1);
    }
}
