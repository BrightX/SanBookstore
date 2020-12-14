package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.ShopDao;
import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐亮亮
 * @since 2020/12/12
 */
@Service
public class ShopServiceImpl implements ShopService {

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
}