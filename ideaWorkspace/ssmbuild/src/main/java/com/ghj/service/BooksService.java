package com.ghj.service;

import com.ghj.pojo.Books;

import java.util.List;

/**
 * @author 86187
 */
public interface BooksService {
    public int addBooks(Books books);

    public Books selectByID(int id);

    public int deleteByID(int id);

    public int updateBooks(Books books);

    public List<Books> selectAll();

    public List<Books> search(String bookName);
}
