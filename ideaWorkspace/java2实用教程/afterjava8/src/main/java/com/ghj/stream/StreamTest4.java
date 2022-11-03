package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 86187
 */
public class StreamTest4 {
    public static void main(String[] args) {
        List<Student> list = StudentsUtil.getList();
        // 使用Collector转化为别的任意集合
        HashSet<String> collect = list.stream().map(Student::getName).collect(Collectors.toCollection(HashSet::new));
        System.out.println(collect);
        // 使用Collector 计算
        Double collect1 = list.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println(collect1);
        DoubleSummaryStatistics collect3 = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
        System.out.println(collect3.getMax());
        System.out.println(collect3.getMin());
        System.out.println(collect3.getAverage());

        // 分组  无线分组
        Map<String, Map<Integer, List<Student>>> collect2 = list.stream().collect(Collectors.groupingBy(Student::getName, Collectors.groupingBy(Student::getAge)));
        System.out.println(collect2);
        // join 连接字符串
        String collect4 = list.stream().map(Student::getName).collect(Collectors.joining(",", "----", "----"));
        String[] split = collect4.split(",");
        System.out.println(collect4);
        System.out.println(Arrays.toString(split));
    }
}
