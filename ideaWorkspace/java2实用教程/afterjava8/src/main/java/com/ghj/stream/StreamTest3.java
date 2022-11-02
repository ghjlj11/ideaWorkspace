package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 86187
 */
public class StreamTest3 {
    public static void main(String[] args) {
        List<Student> list = StudentsUtil.getList();
        Stream<Student> stream = list.stream();
        // reduce()
        Student reduce = stream.reduce(new Student(22, "ww"), (o1, o2) -> new Student(o1.getAge() + o2.getAge(), "qq"));
        System.out.println(reduce);

        // map-reduce
        Integer reduce1 = list.stream().map(Student::getAge).reduce(0, Integer::sum);
        System.out.println(reduce1);
        Optional<Integer> reduce2 = list.stream().map(Student::getAge).reduce(Integer::sum);
        System.out.println(reduce2);
    }
}
