package com.bright.bookstore.dao;

import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.vo.ShopVO;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
public interface ShopDao {
    /**
     * 创建商铺
     *
     * @param shop 商铺
     * @return 创建结果
     */
    int createShop(Shop shop);

    /**
     * 通过用户名查找商铺
     *
     * @param username 用户名
     * @return 商铺
     */
    Shop findShopByUsername(String username);

    ShopVO findShopVoById(int id);

    int getPay(int shopId, double paymentAmount);

    int cashingBalance(int shopId, double amount);
}
