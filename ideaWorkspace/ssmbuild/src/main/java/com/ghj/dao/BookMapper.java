package com.ghj.dao;

import com.ghj.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 86187
 */
public interface BookMapper {

    public int addBooks(Books books);

    public Books selectByID(@Param("id") int id);

    public int deleteByID(@Param("id") int id);

    public int updateBooks(Books books);

    public List<Books> selectAll();

    public List<Books> search(String bookName);

}
