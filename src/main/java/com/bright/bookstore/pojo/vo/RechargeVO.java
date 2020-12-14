package com.bright.bookstore.pojo.vo;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
public class RechargeVO {
    private Integer userId;
    private String username;
    private Double amount;
    private Double balance;
    private String next;

    @Override
    public String toString() {
        return "RechargeVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", next='" + next + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
