package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
        model.addAttribute("bookId", id);
        return "book/detail";
    }

    @GetMapping("/getBookInfo")
    @ResponseBody
    public Book getBookInfo(int id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/buyBook")
    public Map<String, Object> buyBook(HttpSession session, int purchaseQuantity, int bookId, String receiveAddress) {
        // todo 用户买书
        Map<String, Object> result = new HashMap<>(4);
        result.put("next", "order");
        return result;
    }
}
