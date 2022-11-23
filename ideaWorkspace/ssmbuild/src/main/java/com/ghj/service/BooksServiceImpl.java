package com.ghj.service;

import com.ghj.dao.BookMapper;
import com.ghj.pojo.Books;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author 86187
 */
public class BooksServiceImpl implements BooksService {

    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBooks(Books books) {
        return bookMapper.addBooks(books);
    }

    @Override
    public Books selectByID(int id) {
        return bookMapper.selectByID(id);
    }

    @Override
    public int deleteByID(int id) {
        return bookMapper.deleteByID(id);
    }

    @Override
    public int updateBooks(Books books) {
        return bookMapper.updateBooks(books);
    }

    @Override
    public List<Books> selectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public List<Books> search(String bookName) {
        return bookMapper.search("%"+ bookName+ "%");
    }

}
