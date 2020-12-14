package com.bright.bookstore.pojo;

import java.util.Date;

/**
 * 订单
 * @author 徐亮亮
 * @since 2020/12/1
 */
public class Order {
    private int id;
    private String orderNumber;
    private int shopId;
    private String shopName;
    private int userId;
    private String username;
    private int bookId;
    private String bookName;
    private double price;
    private String image;
    private String sendAddress;
    private String receiveAddress;
    private Date createTime;
    private String status;
    private double paymentAmount;
    private int purchaseQuantity;

    public Order() {
    }

    public Order(int id, String orderNumber, int shopId, String shopName, int userId, String username, int bookId, String bookName, double price, String image, String sendAddress, String receiveAddress, Date createTime, String status, double paymentAmount, int purchaseQuantity) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.shopId = shopId;
        this.shopName = shopName;
        this.userId = userId;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.image = image;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
        this.createTime = createTime;
        this.status = status;
        this.paymentAmount = paymentAmount;
        this.purchaseQuantity = purchaseQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", sendAddress='" + sendAddress + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", purchaseQuantity=" + purchaseQuantity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
