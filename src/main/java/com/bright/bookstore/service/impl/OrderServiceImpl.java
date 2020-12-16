package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.BookDao;
import com.bright.bookstore.dao.OrderDao;
import com.bright.bookstore.dao.ShopDao;
import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.Order;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.service.OrderService;
import com.bright.bookstore.utils.OrderGen;
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

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> findOrderByUsername(String username) {
        return orderDao.findOrderByUsername(username);
    }

    @Override
    public List<Order> findOrderByShopId(int shopId) {
        return orderDao.findOrderByShopId(shopId);
    }

    @Override
    public int createOrder(Order order, AuthUser user) {
        // 生成订单号：SWB2020121615375798891000
        String orderNumber = "SWB" + OrderGen.generateOrderNo();
        order.setOrderNumber(orderNumber);
        int result = orderDao.createOrder(order);
        if (result > 0) {
            double paymentAmount = order.getPaymentAmount();
            userDao.pay(user.getUsername(), paymentAmount);
            shopDao.getPay(order.getShopId(), paymentAmount);
            bookDao.minus(order.getBookId(), order.getPurchaseQuantity());
            return 1;
        }
        return 0;
    }
}
