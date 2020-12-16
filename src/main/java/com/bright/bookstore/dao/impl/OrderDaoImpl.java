package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.OrderDao;
import com.bright.bookstore.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findOrderByUsername(String username) {
        String sql = "select * from `order` where username=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), username);
    }

    @Override
    public int createOrder(Order order) {
        // 生成订单
        String sql = "insert into `order`(order_number, username, user_id, shop_name, shop_id, book_name, book_id, " +
                "price, image, send_address, receive_address, status, payment_amount, purchase_quantity) " +
                "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql, order.getOrderNumber(), order.getUsername(), order.getUserId(), order.getShopName(),
                order.getShopId(), order.getBookName(), order.getBookId(), order.getPrice(), order.getImage(),
                order.getSendAddress(), order.getReceiveAddress(), order.getStatus(), order.getPaymentAmount(), order.getPurchaseQuantity());
    }

    @Override
    public List<Order> findOrderByShopId(int shopId) {
        String sql = "select * from `order` where shop_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class), shopId);
    }

    @Override
    public int receive(int orderId) {
        String sql = "update `order` set status='已收货' where id=?";
        return jdbcTemplate.update(sql, orderId);
    }

    @Override
    public int delivery(int orderId) {
        String sql = "update `order` set status='已发货' where id=?";
        return jdbcTemplate.update(sql, orderId);
    }
}
