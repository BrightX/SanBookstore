package com.bright.bookstore.dao;

import com.bright.bookstore.pojo.Order;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
public interface OrderDao {
    List<Order> findOrderByUsername(String username);

    int createOrder(Order order);

    List<Order> findOrderByShopId(int shopId);
}
