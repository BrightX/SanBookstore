package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.ShopCartDao;
import com.bright.bookstore.pojo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
@Repository
public class ShopCartDaoImpl implements ShopCartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(ShopCart shopCart) {
        String sql = "insert into shopcart(book_id, user_id, purchase_quantity) VALUE (?,?,?)";
        int result = jdbcTemplate.update(sql, shopCart.getBookId(), shopCart.getUserId(), shopCart.getPurchaseQuantity());
        return result > 0;
    }

    @Override
    public boolean remove(int id) {
        String sql = "delete from shopcart where id=?";
        int result = jdbcTemplate.update(sql, id);
        return result > 0;
    }

    @Override
    public List<ShopCart> getShopCartListByUserId(int userId) {
        String sql = "select * from shopcart where user_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShopCart.class), userId);
    }
}
