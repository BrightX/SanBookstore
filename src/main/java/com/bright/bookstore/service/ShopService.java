package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;

/**
 * todo 商铺 Service
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
}
