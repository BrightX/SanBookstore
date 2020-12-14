package com.bright.bookstore.pojo.vo;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
public class UserVO {
    Integer id;
    String username;
    Double balance;

    public UserVO() {
    }

    public UserVO(Integer id, String username, Double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
