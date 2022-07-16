package com.ghj.dao;

import com.ghj.pojo.Student;

import java.util.List;

/**
 * @author 86187
 */
public interface StudentMapper {

    List<Student> selectStudent();
    List<Student> selectStudent2();
}
