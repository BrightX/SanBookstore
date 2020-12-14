package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.OrderDao;
import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findOrderByUsername(String username) {
        return orderDao.findOrderByUsername(username);
    }

    @Override
    public List<Order> findOrderByShopId(int shopId) {
        return orderDao.findOrderByShopId(shopId);
    }
}
