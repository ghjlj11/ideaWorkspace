package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * stream的匹配
 * @author 86187
 */
public class StreamTest2 {
    public static void main(String[] args) {
        List<Student> list = StudentsUtil.getList();
        Optional<Student> s = Optional.of(new Student(44 , "ww"));

        // findAny()   findFirst()
        Optional<Student> any = list.stream().findAny();
        Optional<Student> first = list.stream().findFirst();
        boolean b = list.stream().noneMatch(v -> v.getAge() > 14);
        System.out.println(b);

        System.out.println(any + " " + first);

        // max   min   并行流使用多个线程处理
        Optional<Student> max = list.parallelStream().max((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));
        max.ifPresent(System.out::println);
    }
}
