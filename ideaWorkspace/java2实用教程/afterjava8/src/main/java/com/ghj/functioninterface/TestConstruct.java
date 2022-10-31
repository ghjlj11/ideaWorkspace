package com.ghj.functioninterface;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 86187
 * 构造器引用  前提是接口的参数类型需要与 构造器的参数类型一致， 并且接口的返回值是 构造器返回值
 */
public class TestConstruct {
    public static void main(String[] args) {
        BiFunction<Integer, String, Student> biFunction = Student::new;
    }
}
