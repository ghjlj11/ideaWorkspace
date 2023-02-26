package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 86187
 */
public class NewTest {
    public static void main(String[] args) {
        List<Student> list = StudentsUtil.getList();
        long count = list.stream().distinct().count();
        System.out.println(list);
        System.out.println(count);
        System.out.println(null instanceof Object);
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "2");
        System.out.println(map.get("2"));
    }
}
