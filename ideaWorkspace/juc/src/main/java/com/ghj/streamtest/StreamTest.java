package com.ghj.streamtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 86187
 * distinct() 去除数据里面重复的元素， 判断是否重复是根据元素的hashCode()以及equals()方法
 */
public class StreamTest {
    public static void main(String[] args) {
        List<TestEqualsEntity> list = new ArrayList<>();
        list.add(new TestEqualsEntity(12, "aa"));
        list.add(new TestEqualsEntity(13, "bb"));
        list.add(new TestEqualsEntity(13, "bb"));
        Map<TestEqualsEntity, String> map = new HashMap<>();
        map.put( new TestEqualsEntity(12, "aa"), "1");
        map.put( new TestEqualsEntity(12, "aa"), "2");
        map.put( new TestEqualsEntity(12, "aa"), "3");
        Stream<TestEqualsEntity> stream = list.stream();
        List<TestEqualsEntity> collect = stream.distinct().collect(Collectors.toList());
        System.out.println(list);
        System.out.println(collect);
        System.out.println(map);
    }
}
