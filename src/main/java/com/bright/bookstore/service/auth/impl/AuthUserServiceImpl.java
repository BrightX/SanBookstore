package com.bright.bookstore.service.auth.impl;

import com.bright.bookstore.dao.UserDao;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.service.auth.AuthUserService;
import com.bright.bookstore.utils.AuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author 徐亮亮
 * @since 2020/11/26
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public AuthUser login(String username, String rawPassword) {
        // 用户登录
        try {
            AuthUser authUser = userDao.findUser(username);
            if (authUser == null) {
                return null;
            }
            boolean isAuthenticated = AuthenticateUtils.checkPassword(rawPassword, authUser.getPassword());
            if (isAuthenticated) {
                return authUser;
            }
        } catch (DataAccessException e) {
            return null;
        }
        return null;
    }

    @Override
    public AuthUser register(String username, String rawPassword) {
        // 用户注册
        String password = AuthenticateUtils.makePassword(rawPassword);

        return userDao.createUser(username, password);
    }

    @Override
    public AuthUser rePassword(AuthUser user, String rawPassword) {
        String password = AuthenticateUtils.makePassword(rawPassword);
        int result = userDao.rePassword(user.getUsername(), password);
        if (result == 0) {
            return null;
        }
        this.updateAuthUser(user);
        return user;
    }

    @Override
    public void updateAuthUser(AuthUser authUser) {
        AuthUser authUser1 = userDao.findUser(authUser.getUsername());
        if (authUser1 != null) {
            authUser.setId(authUser1.getId());
            authUser.setPassword(authUser1.getPassword());
            authUser.setIsMerchant(authUser1.getIsMerchant());
            authUser.setIsSuperuser(authUser1.getIsSuperuser());
        }
    }

    @Override
    public boolean isExisted(String username) {
        // 判断用户是否存在
        return userDao.isExisted(username);
    }
}
