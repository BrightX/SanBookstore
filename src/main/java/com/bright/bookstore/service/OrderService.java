package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.pojo.user.AuthUser;

import java.util.List;

/**
 * 订单 Service
 *
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface OrderService {
    List<Order> findOrderByUsername(String username);
    List<Order> findOrderByShopId(int shopId);
    int createOrder(Order order, AuthUser user);

    int receive(int orderId);
}
