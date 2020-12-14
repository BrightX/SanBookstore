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
    public boolean createOrder(Order order) {
        // todo 生成订单
        return false;
    }
}
