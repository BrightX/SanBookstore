package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 徐亮亮
 * @since 2020/11/28
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable int id) {
        Book book = bookService.findBookById(id);
        String title = "暂无书籍";
        if (book != null) {
            title = book.getName() + " - 商品详情";
        }
        model.addAttribute("title", title);
        model.addAttribute("book", book);
        return "book/detail";
    }

    @PostMapping("/upload")
    public String uploadTest(MultipartFile file) {
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        try {
            // 保存文件
            file.transferTo(new File("" + originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/getBookInfo")
    @ResponseBody
    public Book getBookInfo(int id) {
        return bookService.findBookById(id);
    }
}
