package com.bright.bookstore.pojo;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
public class ShopCart {
    private int id;
    private int bookId;
    private int userId;
    private int purchaseQuantity;

    public ShopCart() {
    }

    public ShopCart(int id, int bookId, int userId, int purchaseQuantity) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.purchaseQuantity = purchaseQuantity;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", purchaseQuantity=" + purchaseQuantity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
