package com.bright.bookstore.pojo;

import java.util.Date;

/**
 * 书
 * @author 徐亮亮
 * @since 2020/12/1
 */
public class Book {
    private int id;
    private String name;
    private int shopId;
    private String shopName;
    private String info;
    private double price;
    private String image;
    private Date createTime;
    private int inventory;

    public Book() {
    }

    public Book(int id, String name, int shopId, String shopName, String info, double price, String image, Date createTime, int inventory) {
        this.id = id;
        this.name = name;
        this.shopId = shopId;
        this.shopName = shopName;
        this.info = info;
        this.price = price;
        this.image = image;
        this.createTime = createTime;
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", inventory=" + inventory +
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
