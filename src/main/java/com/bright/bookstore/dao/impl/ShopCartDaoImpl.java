package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.ShopCartDao;
import com.bright.bookstore.pojo.ShopCart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
@Repository
public class ShopCartDaoImpl implements ShopCartDao {
    @Override
    public boolean add(ShopCart shopCart) {
        // todo
        return false;
    }

    @Override
    public boolean remove(int bookId) {
        // todo
        return false;
    }

    @Override
    public List<ShopCart> getShopCartListByUserId(int userId) {
        return null;
    }
}
