package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.ShopCartDao;
import com.bright.bookstore.pojo.ShopCart;
import com.bright.bookstore.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartDao shopCartDao;

    @Override
    public boolean add(ShopCart shopCart) {
        return shopCartDao.add(shopCart);
    }

    @Override
    public boolean remove(int shopCartId) {
        return shopCartDao.remove(shopCartId);
    }

    @Override
    public List<ShopCart> getShopCartListByUserId(int userId) {
        return shopCartDao.getShopCartListByUserId(userId);
    }
}
