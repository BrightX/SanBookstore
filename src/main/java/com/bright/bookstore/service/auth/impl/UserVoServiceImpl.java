package com.bright.bookstore.service.auth.impl;

import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.vo.RechargeVO;
import com.bright.bookstore.pojo.vo.UserVO;
import com.bright.bookstore.service.auth.UserVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
@Service
public class UserVoServiceImpl implements UserVoService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO getUserVO(String username) {
        return userDao.getUserVO(username);
    }

    @Override
    public RechargeVO recharge(String username, double amount) {
        return userDao.recharge(username, amount);
    }
}
