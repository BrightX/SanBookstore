package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.user.impl.User;
import com.bright.bookstore.pojo.vo.RechargeVO;
import com.bright.bookstore.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AuthUser findUser(String username) {
        String sql = "select id, username, password, balance, is_merchant, is_superuser from user where username=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public AuthUser createUser(String username, String password) {
        AuthUser authUser = null;
        String sql1 = "insert into user(username, password) VALUES (?, ?)";
        int update = jdbcTemplate.update(sql1, username, password);
        if (update > 0) {
            String sql2 = "select id, username, password, balance, is_merchant, is_superuser from user where username=?";
            authUser = jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<>(User.class), username);
        }
        return authUser;
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param password 要保存到数据库的密码
     * @return 受影响的行数
     */
    @Override
    public int rePassword(String username, String password) {
        String sql = "update user set password=? where username=?";
        return jdbcTemplate.update(sql, password, username);
    }

    /**
     * 修改商家状态
     *
     * @param username 用户名
     * @param isMerchant 是否是商家
     * @return 修改结果
     */
    @Override
    public int setMerchant(String username, boolean isMerchant) {
        String sql = "update user set is_merchant=? where username=?";
        return jdbcTemplate.update(sql, isMerchant, username);
    }

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean isExisted(String username) {
        String sql = "select COUNT(*) from user where username=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    /**
     * @param username 用户名
     * @return UserVO
     */
    @Override
    public UserVO getUserVO(String username) {
        String sql = "select id, username, balance FROM user where username=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserVO.class), username);
    }

    /**
     * todo 用户余额充值
     *
     * @param username 用户名
     * @param amount   充值金额
     */
    @Override
    public RechargeVO recharge(String username, double amount) {
        String sql1 = "update user set balance=balance+? where username=?";
        jdbcTemplate.update(sql1, amount, username);
        String sql2 = "select id as userId, balance from user where username=?";
        RechargeVO rechargeVO = jdbcTemplate.queryForObject(sql2, new BeanPropertyRowMapper<>(RechargeVO.class), username);

        if (rechargeVO == null) {
            rechargeVO = new RechargeVO();
        }

        rechargeVO.setAmount(amount);
        rechargeVO.setUsername(username);
        return rechargeVO;
    }

    /**
     * todo 用户支付
     *
     * @param username 用户名
     * @param amount   充值金额
     * @return 是否支付成功
     */
    @Override
    public boolean pay(String username, double amount) {
        return false;
    }
}
