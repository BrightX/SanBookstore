package com.bright.bookstore.test.utils;

import com.bright.bookstore.utils.OrderGen;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 徐亮亮
 * @since 2020/12/16
 */
@SpringBootTest
public class OrderGenTest {
    @Test
    public void generateOrderNoTest() {
        String orderNo = OrderGen.generateOrderNo();
        System.out.println("orderNo = " + orderNo);
    }
}
