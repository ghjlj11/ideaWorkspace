package com.ghj.controller;

import com.alibaba.fastjson2.JSON;
import com.ghj.dao.BookMapper;
import com.ghj.pojo.Books;
import com.ghj.service.BooksService;
import com.ghj.service.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private  BooksServiceImpl booksService;
    @RequestMapping("/selectAll")
    public String selectAll(Model model){
        List<Books> books = booksService.selectAll();

        model.addAttribute("list",books);
        return "select";
    }

    @RequestMapping("/addBook")
    public String addBook(){
        return "add";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(Books books){
        booksService.addBooks(books);
        return "redirect:/books/selectAll";
    }


    @RequestMapping("/toDeleteBook/{bookID}")
    public String toDeleteBook(@PathVariable("bookID") int bookID){
        System.out.println(bookID);
        booksService.deleteByID(bookID);
        return "redirect:/books/selectAll";
    }

    @RequestMapping("/updateBook")
    public String updateBook(int bookID, Model model){

        Books books = booksService.selectByID(bookID);
        model.addAttribute("books",books);
        return "update";
    }
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Books books){
        booksService.updateBooks(books);
        return "redirect:/books/selectAll";
    }

    @RequestMapping("/search")
    public String search(String bookName, Model model){
        List<Books> books = booksService.search(bookName);
        if(books == null || books.isEmpty()){
            model.addAttribute("msg","未找到");
        }
        model.addAttribute("list",books);
        return "select";
    }

    @RequestMapping("/testAdd")
    public String testAdd(String books){
        Books books1 = JSON.parseObject(books, Books.class);
        return "redirect:/books/selectAll";
    }
}
