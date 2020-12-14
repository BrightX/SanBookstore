package com.bright.bookstore.pojo.user;

/**
 * @author 徐亮亮
 * @since 2020/11/27
 */
public interface AuthUser {
    int getId();

    void setId(int id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    boolean isSuperuser();

    boolean getIsSuperuser();

    void setIsSuperuser(boolean superuser);

    boolean isMerchant();

    boolean getIsMerchant();

    void setIsMerchant(boolean merchant);
}
