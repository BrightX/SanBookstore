package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.OrderVO;
import com.bright.bookstore.service.BookService;
import com.bright.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String detail() {
        return "order/detail";
    }

    @RequestMapping("/getOrder")
    @ResponseBody
    public OrderVO getAllOrder(HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("user");
        List<Order> orderList = orderService.findOrderByUsername(authUser.getUsername());
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        OrderVO orderVO = new OrderVO();
        orderVO.setStatus(200);
        orderVO.setOrderList(orderList);
        orderVO.setTotal(orderList.size());
        return orderVO;
    }

    @GetMapping("/create")
    public String create(Model model, int bookId) {
        Book book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        // todo
        return "";
    }
}
