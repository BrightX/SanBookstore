package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.ShopVO;
import com.bright.bookstore.pojo.vo.UserVO;
import com.bright.bookstore.service.BookService;
import com.bright.bookstore.service.OrderService;
import com.bright.bookstore.service.ShopService;
import com.bright.bookstore.service.auth.UserVoService;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserVoService userVoService;

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
    @ResponseBody
    public Map<String, Object> buyBook(HttpSession session, int purchaseQuantity, int bookId, String receiveAddress) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>(4);
        result.put("username", user.getUsername());
        result.put("userId", user.getId());

        if (receiveAddress == null) {
            result.put("status", -1);
            result.put("msg", "收货地址不能为空");
            return result;
        }
        if (purchaseQuantity < 1) {
            result.put("status", -1);
            result.put("msg", "购买数量不能小于1");
            return result;
        }
        if (bookId < 1) {
            result.put("status", -1);
            result.put("msg", "商品数据异常");
            return result;
        }

        Book book = bookService.findBookById(bookId);

        if (book == null) {
            result.put("status", -2);
            result.put("msg", "商品数据异常");
            return result;
        }

        if (book.getInventory() < purchaseQuantity) {
            result.put("status", -3);
            result.put("msg", "商品库存不足");
            return result;
        }

        ShopVO shopVO = shopService.findShopVoById(book.getShopId());

        if (shopVO == null) {
            result.put("status", -3);
            result.put("msg", "商品数据异常");
            return result;
        }

        double paymentAmount = book.getPrice() * purchaseQuantity;
        UserVO userVO = userVoService.getUserVO(user.getUsername());
        if (paymentAmount > userVO.getBalance()) {
            result.put("status", -5);
            result.put("msg", "当前用户账户余额不足");
            return result;
        }

        Order order = new Order();
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setShopId(shopVO.getId());
        order.setShopName(shopVO.getName());
        order.setSendAddress(shopVO.getSendAddress());
        order.setReceiveAddress(receiveAddress);
        order.setBookId(bookId);
        order.setBookName(book.getName());
        order.setPrice(book.getPrice());
        order.setImage(book.getImage());
        order.setPaymentAmount(paymentAmount);
        order.setPurchaseQuantity(purchaseQuantity);
        order.setStatus("待发货");

        int createOrder = orderService.createOrder(order, user);

        if (createOrder > 0) {
            result.put("status", 200);
            result.put("msg", "购买成功");
        } else {
            result.put("status", -10);
            result.put("msg", "购买失败");
        }

        result.put("next", "order");
        return result;
    }
}
