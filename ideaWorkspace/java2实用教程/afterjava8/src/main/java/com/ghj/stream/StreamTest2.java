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
        List<Student> list = new ArrayList<>();
        list.add(new Student(12, "kk"));
        list.add(new Student(13, "jj"));
        list.add(new Student(14, "ll"));
        Optional<Student> s = Optional.of(new Student(44 , "ww"));

        Optional<Student> any = list.stream().findAny();
        Optional<Student> first = list.stream().findFirst();
        boolean b = list.stream().noneMatch(v -> v.getAge() > 14);
        System.out.println(b);

        System.out.println(any + " " + first);
    }
}
