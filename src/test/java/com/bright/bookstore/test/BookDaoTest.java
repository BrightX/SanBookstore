package com.bright.bookstore.test;

import com.bright.bookstore.dao.BookDao;
import com.bright.bookstore.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐亮亮
 * @since 2020/12/9
 */
@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void findBookByIdsTest() {
        List<Integer> bookIds = new ArrayList<>(4);
        bookIds.add(1);
        bookIds.add(2);
        bookIds.add(3);
        bookIds.add(4);
        System.out.println("bookIds = " + bookIds);
        List<Book> books = bookDao.findBookByIds(bookIds);
        System.out.println("books = " + books);
    }
}
