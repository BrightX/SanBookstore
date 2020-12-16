package com.bright.bookstore.pojo.vo;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.ShopCart;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
public class ShopCartVO {
    private Integer userId;
    private List<ShopCart> shopCartList;
    private List<Book> bookList;

    public ShopCartVO() {
    }

    @Override
    public String toString() {
        return "ShopCartVO{" +
                "userId=" + userId +
                ", shopCartList=" + shopCartList +
                ", bookList=" + bookList +
                '}';
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ShopCart> getShopCartList() {
        return shopCartList;
    }

    public void setShopCartList(List<ShopCart> shopCartList) {
        this.shopCartList = shopCartList;
    }
}
