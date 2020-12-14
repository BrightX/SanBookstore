package com.bright.bookstore.service.auth;

import com.bright.bookstore.pojo.user.AuthUser;

/**
 * @author 徐亮亮
 * @since 2020/11/26
 */
public interface AuthUserService {
    AuthUser login(String username, String password);

    AuthUser register(String username, String password);

    AuthUser rePassword(AuthUser user, String rawPassword);

    void updateAuthUser(AuthUser authUser);

    boolean isExisted(String username);
}
