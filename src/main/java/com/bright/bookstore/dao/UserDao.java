package com.bright.bookstore.dao;

import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.RechargeVO;
import com.bright.bookstore.pojo.vo.UserVO;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface UserDao {
    /**
     * 查询用户
     *
     * @param username 用户名
     * @return AuthUser
     */
    AuthUser findUser(String username);

    /**
     * 添加用户
     *
     * @param username 用户名
     * @param password 密码
     * @return AuthUser
     */
    AuthUser createUser(String username, String password);

    /**
     * 修改密码
     * @param username 用户名
     * @param password 要保存到数据库的密码
     * @return 受影响的行数
     */
    int rePassword(String username , String password);

    /**
     * 修改商家状态
     *
     *
     * @param username 用户名
     * @param isMerchant 是否是商家
     * @return 修改结果
     */
    int setMerchant(String username, boolean isMerchant);

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean isExisted(String username);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return UserVO
     */
    UserVO getUserVO(String username);

    /**
     * 用户余额充值
     * @param username 用户名
     * @param amount 充值金额
     * @return 充值结果
     */
    RechargeVO recharge(String username, double amount);

    /**
     * 用户支付
     * @param username 用户名
     * @param amount 充值金额
     * @return 是否充值成功
     */
    int pay(String username, double amount);
}
