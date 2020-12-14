package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.service.BookService;
import com.bright.bookstore.service.OrderService;
import com.bright.bookstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 管理商铺
 *
 * @author 徐亮亮
 * @since 2020/12/14
 */
@RestController
@RequestMapping("/manageShop")
public class ShopManageController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/getBooks")
    public Map<String, Object> getBooks(HttpSession session) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>(4);
        Shop shop = shopService.findShopByUsername(user.getUsername());
        result.put("shop", shop);
        List<Book> bookList = bookService.findAllBookByShopId(shop.getId());
        result.put("bookList", bookList);
        result.put("total", bookList.size());
        return result;
    }

    @GetMapping(value = "/getOrders")
    public Map<String, Object> getOrders(HttpSession session) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>(4);
        Shop shop = shopService.findShopByUsername(user.getUsername());
        result.put("shop", shop);
        List<Order> orderList = orderService.findOrderByShopId(shop.getId());
        result.put("orderList", orderList);
        result.put("total", orderList.size());
        return result;
    }

    @GetMapping("/getShop")
    public Map<String, Object> getShop(HttpSession session) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Map<String, Object> result = new HashMap<>(4);
        Shop shop = shopService.findShopByUsername(user.getUsername());
        result.put("shop", shop);
        return result;
    }

    @PostMapping("/addBook")
    public Map<String, Object> addBook(HttpSession session, String name, String info,
                                       double price, int inventory, MultipartFile image) {
        Map<String, Object> result = new HashMap<>(4);
        AuthUser user = (AuthUser) session.getAttribute("user");

        String originalFilename = image.getOriginalFilename();
        if (originalFilename == null) {
            originalFilename = "a.jpg";
        }
        String[] split = originalFilename.split("\\.");
        String suffix = split[split.length - 1];

        String baseDir = "static/upload";
        File pathFile = new File(baseDir);
        pathFile.mkdirs();
        String absolutePath = pathFile.getAbsolutePath();

        String imageName = UUID.randomUUID() + "." + suffix;
        String imagePath = baseDir + "/" + imageName;
        String imageAbsolutePath = absolutePath + "/" + imageName;
        try {
            File file = new File(imageAbsolutePath);
            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            imagePath = "static/img/小瞬间.jpg";
        }

        Book book = new Book();
        book.setName(name);
        book.setInfo(info);
        book.setPrice(price);
        book.setInventory(inventory);
        book.setImage(imagePath);

        bookService.addBook(user, book);
        result.put("status", 200);
        result.put("msg", "OK");
        return result;
    }
}
