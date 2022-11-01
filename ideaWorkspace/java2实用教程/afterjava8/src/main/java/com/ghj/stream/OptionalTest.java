package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * @author 86187
 */
public class OptionalTest {
    public static void main(String[] args) throws Exception {
        Student oo = new Student(12, "oo");
        Optional<Student> optionalStudent = Optional.of(oo);
        Class<Optional> optionalClass = Optional.class;
        Constructor<Optional> constructor = optionalClass.getDeclaredConstructor(Object.class);
        constructor.setAccessible(true);
        Optional optional = constructor.newInstance(oo);
        System.out.println(constructor);
        System.out.println(optional);
        System.out.println(optionalStudent.orElseThrow(() -> new Exception("xxx")));
    }
}
