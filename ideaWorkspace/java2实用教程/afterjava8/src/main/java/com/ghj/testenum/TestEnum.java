package com.ghj.testenum;

import com.ghj.pojo.TestExtendPojo;
import com.ghj.pojo.TestHashCodePojo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/3/19 16:06
 */
public class TestEnum {
    public static void main(String[] args) {
        EntityStatus update = EntityStatus.Update;
        Object o = new Object();
        System.out.println(update);
        System.out.println(update.toString());
        System.out.println("Update".equals(update.toString()));
        Class<EntityStatus> aClass = EntityStatus.class;
        System.out.println(aClass.getAnnotation(Component.class));
        Set<String> eyeModelIdSet = new HashSet<>();
        eyeModelIdSet.add(null);
        boolean add = eyeModelIdSet.add(null);
        System.out.println(eyeModelIdSet.contains(null));
        String s = "000";
        BigDecimal bigDecimal = new BigDecimal(s);
        System.out.println(bigDecimal);

        String PATTERN = "\\d{1,30}";
        System.out.println(Pattern.matches(PATTERN, "000"));
        String s2 = "123";
        test(s2);
        System.out.println(s2);

        File file = new File("afterjava8\\src\\main\\resources\\ssh\\aa");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        System.out.println(Arrays.toString(file.listFiles(File::isDirectory)));
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        //list.addAll(null);
        System.out.println(list);
        String format = String.format("%s(%s, %s, %s)", "cat", "2", "3", "4");
        System.out.println(format);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(9);
        list1.add(4);
        list1.add(6);
        List<Integer> sorted = list1.stream().sorted(Comparator.comparingInt(i -> new Integer(i))).collect(Collectors.toList());
        System.out.println(sorted);
        StringBuilder stringBuilder = new StringBuilder("1-2-");
        System.out.println("ss:" + stringBuilder.substring(0, stringBuilder.length() - 1));
        Integer integer = 3;
        BigDecimal bigDecimal1 = new BigDecimal("1.23");
        System.out.println("integer:" + integer);
        System.out.println("bigDecimal1:" + bigDecimal1);

        Integer[] integers = list1.toArray(new Integer[list1.size()]);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        System.out.println(set.add(1));

        String kk = "q111__";
        System.out.println(Arrays.toString(kk.split("__")));
        System.out.println(kk.endsWith("__"));

    }

    private static List<? extends TestHashCodePojo> test (String s2) {
        s2 = "333";
        List<TestExtendPojo> list = new ArrayList<>();
        List<TestExtendPojo> result = new ArrayList<>();
        return result;
    }

}
