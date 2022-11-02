package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86187
 */
public class StreamTest4 {
    public static void main(String[] args) {
        List<Student> list = StudentsUtil.getList();
        HashSet<String> collect = list.stream().map(Student::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);
    }
}
