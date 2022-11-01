package com.ghj.functioninterface;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 测试方法引用
 * @author 86187
 *  使用方法引用的前提是 需要实现的方法的 入参以及返回值  需要和引用的方法的 入参以及返回值相对应
 *  或者在function接口apply方法的返回值是使用的 方法参数的方法， 也可以使用  类名::方法名  的方式
 *
 *  Bi接口使用方法引用前提是  需要第一个参数是方法体中方法的调用者， 第二个参数是方法的参数， 返回值也是需要一样。
 */
public class TestConsumer {
    public static void main(String[] args) {
        Student student = new Student(3 , "lj");
        // 测试实例的方法引用
        PrintStream out = System.out;
        Consumer<String> consumer = out::println;
        consumer.accept("12345");
        Supplier<Integer> supplier = student::getAge;
        System.out.println("测试consumer: " + supplier.get());

        Function<Student, Integer> function = Student::getAge;
        System.out.println("测试function方法引用:" + function.apply(student));
        // 测试类的静态方法
        Comparator<Integer> comparator = Integer::compare;
        System.out.println("测试Comparator: " + comparator.compare(1, 4));

        // 测试Bi接口
        BiPredicate<String, String> biPredicate = String::equals;
        System.out.println("测试BiPredicate: " + biPredicate.test("12", "12"));

        long l = 4L;
        int g = 4;
        System.out.println(l == g);
    }
}
