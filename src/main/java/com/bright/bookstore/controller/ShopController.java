package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.ShopVO;
import com.bright.bookstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopCart")
    public String shopCart() {
        return "shop/shopCart";
    }

    @GetMapping("/manageShop")
    public String manageShop() {
        return "shop/manageShop";
    }

    @PostMapping("/applyShop")
    @ResponseBody
    public Shop applyShop(HttpSession session, String name, String sendAddress) {
        AuthUser user = (AuthUser) session.getAttribute("user");
        Shop shop = new Shop();
        shop.setName(name);
        shop.setSendAddress(sendAddress);
        shop.setUsername(user.getUsername());
        shop.setUserId(user.getId());
        shopService.applyShop(user, shop);
        session.setAttribute("user", user);
        return shop;
    }

    @GetMapping("/getShopInfo")
    @ResponseBody
    private ShopVO getShopVO(int id) {
        return shopService.findShopVoById(id);
    }
}
