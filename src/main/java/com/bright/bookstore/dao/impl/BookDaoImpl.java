package com.bright.bookstore.dao.impl;

import com.bright.bookstore.dao.BookDao;
import com.bright.bookstore.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐亮亮
 * @since 2020/12/2
 */
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Book> findAllBook() {
        String sql = "select id, name, shop_id, shop_name, info, price, image, create_time, inventory from book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public int createBook(Book book) {
        String sql = "insert into book(name, shop_id, shop_name, info, price, image, inventory) value (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, book.getName(), book.getShopId(), book.getShopName(), book.getInfo(),
                book.getPrice(), book.getImage(), book.getInventory());
    }

    @Override
    public int minus(int bookId, int purchaseQuantity) {
        String sql = "update book set inventory=inventory-? where id=?";
        return jdbcTemplate.update(sql, purchaseQuantity, bookId);
    }

    @Override
    public Book findBookById(int bookId) throws EmptyResultDataAccessException {
        String sql = "select id, name, shop_id, shop_name, info, price, image, create_time, inventory from book where id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
    }

    @Override
    public List<Book> findBookByIds(List<Integer> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            return new ArrayList<>();
        }
        String sql = "select id, name, shop_id, shop_name, info, price, image, create_time, inventory from book where id in (:ids)";
        Map<String, List<Integer>> ids = new HashMap<>(1);
        ids.put("ids", bookIds);
        return namedParameterJdbcTemplate.query(sql, ids, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public List<Book> findAllBookByShopId(int shopId) {
        String sql = "select * from book where shop_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), shopId);
    }
}
