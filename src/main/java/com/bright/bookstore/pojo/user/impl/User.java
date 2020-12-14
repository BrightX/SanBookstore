package com.bright.bookstore.pojo.user.impl;

import com.bright.bookstore.pojo.user.AuthUser;

/**
 * @author 徐亮亮
 * @since 2020/11/27
 */
public class User implements AuthUser {
    int id;
    String username;
    String password;
    boolean isSuperuser;
    boolean isMerchant;
    double balance;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isSuperuser() {
        return isSuperuser;
    }

    @Override
    public boolean getIsSuperuser() {
        return isSuperuser;
    }

    @Override
    public void setIsSuperuser(boolean superuser) {
        isSuperuser = superuser;
    }

    @Override
    public boolean isMerchant() {
        return isMerchant;
    }

    @Override
    public boolean getIsMerchant() {
        return isMerchant;
    }

    @Override
    public void setIsMerchant(boolean merchant) {
        isMerchant = merchant;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", isSuperuser=" + isSuperuser +
                ", isMerchant=" + isMerchant +
                ", balance=" + balance +
                '}';
    }
}
