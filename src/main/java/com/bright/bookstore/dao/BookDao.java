package com.bright.bookstore.dao;

import com.bright.bookstore.pojo.Book;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
public interface BookDao {
    List<Book> findAllBook();
    int createBook(Book book);
    int minus(int bookId, int purchaseQuantity);
    Book findBookById(int bookId);
    List<Book> findBookByIds(List<Integer> bookIds);

    List<Book> findAllBookByShopId(int shopId);
}
