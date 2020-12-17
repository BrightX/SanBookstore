package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.ShopVO;

/**
 * 商铺 Service
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface ShopService {
    /**
     * 申请商铺
     * @param user 用户
     * @param shop 商铺
     */
    void applyShop(AuthUser user, Shop shop);

    /**
     * 通过用户名查找商铺
     *
     * @param username 用户名
     * @return shop
     */
    Shop findShopByUsername(String username);

    /**
     * 查询shopVO 通过 shop id
     *
     * @param id shop id
     * @return ShopVO
     */
    ShopVO findShopVoById(int id);

    int cashingBalance(AuthUser user, Shop shop, double amount);

    int delivery(int orderId);
}
