package com.bright.bookstore.dao;

import com.bright.bookstore.pojo.ShopCart;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
public interface ShopCartDao {

    boolean add(ShopCart shopCart);
    boolean remove(int bookId);
    List<ShopCart> getShopCartListByUserId(int userId);
}
