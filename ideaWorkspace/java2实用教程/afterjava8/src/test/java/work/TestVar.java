package work;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 测试 代码块中声明的变量
 */
public class TestVar {
    int a = 6;
    {
        int a = 3;
        this.a = 44;
        System.out.println("inner:" + a);
    }
    public TestVar(){
        int a = 12;
        System.out.println("constructor:" + a);
    }

    public static void main(String[] args) {
        TestVar testVar = new TestVar();
        System.out.println(testVar.a);
        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.DAY_OF_WEEK);
        System.out.println(i);
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));
        System.out.println( i = 3);
        System.out.println(i);
        Map<String, String> map = new HashMap<>(6);
        map.put("1", "111");
        System.out.println(map.remove("2"));
        System.out.println(map);

        String sd = "02";
        System.out.println(1-3);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(date));

        String s = "12.00";
        BigDecimal bigDecimal = new BigDecimal(s);
        //System.out.println(bigDecimal.add(null));

        List<String> list = new ArrayList<>(8);
        //list.add(2, "haha");
        System.out.println(list);
        BigDecimal[] bigDecimals = new BigDecimal[10];
        bigDecimals[2] = new BigDecimal(3);
        for (BigDecimal decimal : bigDecimals) {

            System.out.println(decimal);
        }
        System.out.println(Integer.MAX_VALUE);
        List<String> list1 = new ArrayList<>();
        list1.add(null);
        list1.add(null);
        list1.add(null);
        list1.add("ddd");
        System.out.println(list1);
    }
}
