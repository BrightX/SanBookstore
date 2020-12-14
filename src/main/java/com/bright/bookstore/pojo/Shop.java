package com.bright.bookstore.pojo;

/**
 * 商铺
 *
 * @author 徐亮亮
 * @since 2020/12/1
 */
public class Shop {
    private int id;
    private String name;
    private String sendAddress;
    private double balance;
    private String username;
    private int userId;

    public Shop() {
    }

    public Shop(int id, String name, String sendAddress, double balance, String username, int userId) {
        this.id = id;
        this.name = name;
        this.sendAddress = sendAddress;
        this.balance = balance;
        this.username = username;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sendAddress='" + sendAddress + '\'' +
                ", balance=" + balance +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
