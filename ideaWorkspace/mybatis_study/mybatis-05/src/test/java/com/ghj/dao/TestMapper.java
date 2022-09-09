package com.ghj.dao;

import com.ghj.pojo.Student;
import com.ghj.pojo.Teacher;
import com.ghj.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMapper {
    @Test
    public void test01(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.getClass() + "<=====");
        List<Student> students = mapper.selectStudent();
        for ( Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
    @Test
    public void test02(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectStudent2();
        for ( Student student : students) {
            System.out.println(student);
        }
        sqlSession.close();
    }
}
