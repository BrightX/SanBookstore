package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.ShopCart;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.ShopCartVO;
import com.bright.bookstore.service.BookService;
import com.bright.bookstore.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
@RestController
@RequestMapping("/shopCart")
public class ShopCartController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ShopCartService shopCartService;

    @PostMapping("/add")
    public Map<String, Object> add(HttpSession session, @RequestParam int bookId, @RequestParam int quantity) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>(4);
        result.put("userId", user.getId());
        result.put("username", user.getUsername());

        ShopCart shopCart = new ShopCart();
        shopCart.setBookId(bookId);
        shopCart.setUserId(user.getId());
        shopCart.setPurchaseQuantity(quantity);
        result.put("result", shopCartService.add(shopCart));

        return result;
    }

    @GetMapping("/remove")
    public ShopCartVO remove(HttpSession session, @RequestParam int bookId) {
        AuthUser user = (AuthUser) session.getAttribute("user");

        shopCartService.remove(bookId);

        List<ShopCart> shopCartList = shopCartService.getShopCartListByUserId(user.getId());
        ShopCartVO shopCartVO = new ShopCartVO();
        shopCartVO.setUserId(user.getId());
        shopCartVO.setShopCartList(shopCartList);
        return shopCartVO;
    }

    @GetMapping("/get")
    public ShopCartVO getShopCart(HttpSession session) {
        AuthUser user = (AuthUser) session.getAttribute("user");

        List<ShopCart> shopCartList = shopCartService.getShopCartListByUserId(user.getId());
        List<Integer> bookIds = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        if (shopCartList != null) {
            shopCartList.forEach(shopCart -> bookIds.add(shopCart.getBookId()));
            bookList = bookService.findBookByIds(bookIds);
        }

        ShopCartVO shopCartVO = new ShopCartVO();
        shopCartVO.setUserId(user.getId());
        shopCartVO.setShopCartList(shopCartList);
        shopCartVO.setBookList(bookList);
        return shopCartVO;
    }

}
