package com.ghj.streamtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 86187
 * stream流
 * filter 过滤， 参数为断言型函数接口
 * map 传入Stream泛型里的一个对象， 转化为一个任意的值
 * sorted 排序输出， 传入一个比较器
 * limit 指定输出前几个
 * 终止操作 只能有 一次！
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student(11, "A", 12), new Student(22, "B", 13), new Student(33, "C", 14));
        Stream<Student> stream = list.stream();
        Stream<String> limit = stream.filter(n -> n.getAge() > 11)
                .map(n -> n.getName().toLowerCase())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(2);
        //相当于终止了， 接下来就不能使用这个流了
        limit.forEach(System.out::println);
    }
}
