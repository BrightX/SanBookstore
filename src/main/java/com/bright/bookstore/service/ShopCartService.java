package com.bright.bookstore.service;

import com.bright.bookstore.pojo.ShopCart;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
public interface ShopCartService {
    boolean add(ShopCart shopCart);
    boolean remove(int shopCartId);
    List<ShopCart> getShopCartListByUserId(int userId);
}
