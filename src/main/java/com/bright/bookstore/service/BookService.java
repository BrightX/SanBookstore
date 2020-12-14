package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.user.AuthUser;

import java.util.List;

/**
 * todo 书 service
 *
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface BookService {
    List<Book> findAllBook();

    List<Book> findAllBookByShopId(int shopId);

    Book findBookById(int bookId);

    void addBook(AuthUser user, Book book);

    List<Book> findBookByIds(List<Integer> bookIds);
}
