package com.bright.bookstore.service;

import com.bright.bookstore.pojo.Book;

import java.util.List;

/**
 * todo 书 service
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface BookService {
    List<Book> findAllBook();

    Book findBookById(int bookId);
    List<Book> findBookByIds(List<Integer> bookIds);
}
