package com.ghj.functioninterface;

import java.util.function.Predicate;

/**
 * @author 86187
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = v -> v != null && v.length() > 5 && v.length() < 10;
        // 判断字符串是否符合该 Predicate
        String s = "null23";
        System.out.println(predicate.test(s));
        Predicate<String> negate = predicate.negate();
        System.out.println(negate.test(s));
    }
}
