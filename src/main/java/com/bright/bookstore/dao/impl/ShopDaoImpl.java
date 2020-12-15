package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.ShopDao;
import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 徐亮亮
 * @since 2020/12/12
 */
@Repository
public class ShopDaoImpl implements ShopDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建商铺
     *
     * @param shop 商铺
     * @return 创建结果
     */
    @Override
    public int createShop(Shop shop) {
        String sql = "insert into shop(name, send_address, username, user_id) VALUE (?,?,?,?)";
        return jdbcTemplate.update(sql, shop.getName(), shop.getSendAddress(), shop.getUsername(), shop.getUserId());
    }

    /**
     * 通过用户名查找商铺
     *
     * @param username 用户名
     * @return 商铺
     */
    @Override
    public Shop findShopByUsername(String username) {
        String sql = "select * from shop where username=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Shop.class), username);
    }

    @Override
    public ShopVO findShopVoById(int id) {
        String sql = "select id, name, send_address from shop where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ShopVO.class), id);
    }
}
