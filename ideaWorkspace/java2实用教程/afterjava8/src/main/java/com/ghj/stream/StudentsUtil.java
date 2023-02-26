package com.ghj.stream;

import com.ghj.functioninterface.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class StudentsUtil {
    public static List<Student> getList(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(12, "kk"));
        list.add(new Student(13, "jj"));
        list.add(new Student(14, "ll"));
        list.add(new Student(14, "ll"));
        return list;
    }
}
