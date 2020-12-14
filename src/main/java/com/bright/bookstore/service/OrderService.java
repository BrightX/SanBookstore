package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Order;

import java.util.List;

/**
 * todo 订单 Service
 *
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface OrderService {
    List<Order> findOrderByUsername(String username);
}
