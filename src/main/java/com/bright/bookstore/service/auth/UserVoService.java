package com.bright.bookstore.service.auth;

import com.bright.bookstore.pojo.vo.RechargeVO;
import com.bright.bookstore.pojo.vo.UserVO;

/**
 * @author 徐亮亮
 * @since 2020/12/1
 */
public interface UserVoService {
    UserVO getUserVO(String username);
    RechargeVO recharge(String username, double amount);
}
