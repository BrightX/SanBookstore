package com.bright.bookstore.test.utils;

import com.bright.bookstore.utils.AuthenticateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

/**
 * @author 徐亮亮
 * @since 2020/11/26
 */
@SpringBootTest
public class AuthenticateUtilsTest {

    /**
     * 测试密码加密与验证
     */
    @Test
    public void testCheckPassword() {
        String rawPassword = "This is my password";
        String badPassword = "This isn`t my password";

        String makePassword = AuthenticateUtils.makePassword(rawPassword);

        boolean checked = AuthenticateUtils.checkPassword(rawPassword, makePassword);
        Assertions.assertTrue(checked);

        boolean notChecked = AuthenticateUtils.checkPassword(badPassword, makePassword);
        Assertions.assertFalse(notChecked);

        Logger logger = Logger.getLogger(this.getClass().getName());
        String msg = "测试通过";
        logger.info(msg);
    }
}
