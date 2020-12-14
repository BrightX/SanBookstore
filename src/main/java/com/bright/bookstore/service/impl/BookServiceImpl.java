package com.bright.bookstore.service.impl;

import com.bright.bookstore.dao.BookDao;
import com.bright.bookstore.dao.ShopDao;
import com.bright.bookstore.pojo.Book;
import com.bright.bookstore.pojo.Shop;
import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Book> findAllBook() {
        return bookDao.findAllBook();
    }

    @Override
    public List<Book> findAllBookByShopId(int shopId) {
        return bookDao.findAllBookByShopId(shopId);
    }

    @Override
    public Book findBookById(int bookId) {
        Book book = null;
        try {
            book = bookDao.findBookById(bookId);
        } catch (EmptyResultDataAccessException ignored) {
        }
        return book;
    }

    @Override
    public void addBook(AuthUser user, Book book) {
        Shop shop = shopDao.findShopByUsername(user.getUsername());
        book.setShopId(shop.getId());
        book.setShopName(shop.getName());
        bookDao.createBook(book);
    }

    @Override
    public List<Book> findBookByIds(List<Integer> bookIds) {
        return bookDao.findBookByIds(bookIds);
    }
}
