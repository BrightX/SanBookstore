package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/11/27
 */
@Controller
public class IndexController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<Book> books = bookService.findAllBook();
        model.addAttribute("Books", books);
        return "index";
    }
}
