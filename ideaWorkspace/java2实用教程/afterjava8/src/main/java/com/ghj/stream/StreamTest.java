package com.ghj.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 86187
 *  stream 创建
 */
public class StreamTest {
    public static void main(String[] args) {
        // 数组创建
        String[] s = new String[]{"12", "23"};
        Stream<String> stream = Arrays.stream(s);

        // 无限流
        Stream<Integer> iterate = Stream.iterate(0, (v) -> v + 2);
        iterate.forEach(System.out::println);
    }
}
