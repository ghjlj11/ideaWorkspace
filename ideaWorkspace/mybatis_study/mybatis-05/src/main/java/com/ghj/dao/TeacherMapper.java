package com.ghj.dao;

import com.ghj.pojo.Teacher;
import org.apache.ibatis.annotations.Select;

/**
 * @author 86187
 */
public interface TeacherMapper {

//    @Select("select * from teacher")
    Teacher selectTeacher();
}
