package workTest;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 86187
 */
public class Test1 {
    private int s;
    public static void main(String[] args) throws Exception {
        int s = 2;
        StringBuilder k = new StringBuilder("1234");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list.forEach(i -> {
            if(i == 3){
                list1.add(i);
                k.append(i);
            }
        });
        System.out.println(list1);
        System.out.println(k);
        Stream<Integer> stream = list.stream();
        Stream<String> collect = stream.filter(v -> v > 3).map(Object::toString);
        System.out.println(collect);
        Map<String, Object> map = new HashMap<>();
        int e = 9;
        String s1 = Integer.toBinaryString(e ^ 2);
        System.out.println(s1);

        String pattern = "^-?\\d+(\\.\\d+)?$";
        Random random = new Random();
        while (true){
            String s2 = random.nextDouble() + "";
            boolean matches = Pattern.matches(pattern, s2);
            if(!matches){
                System.out.println(s2);
                throw new Exception();
            }
        }
    }
}
