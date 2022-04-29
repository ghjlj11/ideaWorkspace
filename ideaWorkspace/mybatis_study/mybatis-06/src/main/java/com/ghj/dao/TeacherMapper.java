package com.ghj.dao;

import com.ghj.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86187
 */
public interface TeacherMapper {

    Teacher getTeacher(@Param("id") int id);
    Teacher getTeacher2(@Param("id") int id);
}
