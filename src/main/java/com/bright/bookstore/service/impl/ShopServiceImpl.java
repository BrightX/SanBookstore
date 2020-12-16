package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.OrderDao;
import com.bright.bookstore.dao.ShopDao;
import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.ShopVO;
import com.bright.bookstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author 徐亮亮
 * @since 2020/12/12
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    /**
     * 申请商铺
     *
     * @param user 用户
     * @param shop 商铺
     */
    @Override
    public void applyShop(AuthUser user, Shop shop) {
        shop.setUsername(user.getUsername());
        shop.setUserId(user.getId());
        int result = shopDao.createShop(shop);
        if (result > 0) {
            Shop shop1 = shopDao.findShopByUsername(user.getUsername());
            if (shop1 != null) {
                shop.setId(shop1.getId());
                shop.setBalance(shop1.getBalance());
                int result1 = userDao.setMerchant(user.getUsername(), true);
                if (result1 > 0) {
                    user.setIsMerchant(true);
                }
            }
        } else {
            shop.setId(-1);
        }
    }

    /**
     * 通过用户名查找商铺
     *
     * @param username 用户名
     * @return shop
     */
    @Override
    public Shop findShopByUsername(String username) {
        Shop shop = null;
        try {
            shop = shopDao.findShopByUsername(username);
        } catch (EmptyResultDataAccessException ignore) {
        }
        return shop;
    }

    /**
     * 查询shopVO 通过 shop id
     *
     * @param id shop id
     * @return ShopVO
     */
    @Override
    public ShopVO findShopVoById(int id) {
        try {
            return shopDao.findShopVoById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ShopVO(-1, null, null);
        }
    }

    @Override
    public int cashingBalance(AuthUser user, Shop shop, double amount) {
        userDao.recharge(user.getUsername(), amount);
        int result1 = shopDao.cashingBalance(shop.getId(), amount);
        if (result1 > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int delivery(int orderId) {
        return orderDao.delivery(orderId);
    }
}
