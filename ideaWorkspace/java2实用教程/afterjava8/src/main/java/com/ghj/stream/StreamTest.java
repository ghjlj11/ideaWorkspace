package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 86187
 *  stream 创建
 */
public class StreamTest {
    public static void main(String[] args) {
        Stream<Integer> parallelStream = Arrays.asList(1,2,3,4,5,6,7).parallelStream();
        IntSummaryStatistics statistics = parallelStream.mapToInt(x -> x).summaryStatistics();
        System.out.println(statistics);

        // 数组创建
        Character[] c = new Character[]{'a', 'b', 'c'};
        String[] s = new String[]{"12", "23"};
        Stream<Character> stream = Arrays.stream(c);

        // 无限流
//        Stream<Integer> iterate = Stream.iterate(0, (v) -> v + 2);
//        iterate.forEach(System.out::println);

        List<Student> list = Arrays.asList(new Student(2, "gg"), new Student(3, "ll"), new Student(2, "jj"));
        Map<Integer, List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(map);
        List<Integer> collect = list.stream().map(Student::getAge).collect(Collectors.toList());
        System.out.println(collect);
        String n = "1,2,3,4";
        String[] split = n.split(",");
        System.out.println(split);
        List<String> list1 = Arrays.asList("ad");
        Stream<Character> characterStream = list.stream().flatMap(x -> changStringCharacter(x.getName()));
    }
    public static Stream<Character> changStringCharacter(String s){
        Character[] characters = new Character[s.length()];
        int i = 0 ;
        for (char c : s.toCharArray()) {
            characters[i ++] = c;
        }
        return Arrays.stream(characters);
    }
}
