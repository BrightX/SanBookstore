package com.bright.bookstore.controller;

import com.bright.bookstore.pojo.user.AuthUser;
import com.bright.bookstore.pojo.vo.RechargeVO;
import com.bright.bookstore.pojo.vo.UserVO;
import com.bright.bookstore.service.auth.AuthUserService;
import com.bright.bookstore.service.auth.UserVoService;
import com.bright.bookstore.utils.AuthenticateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐亮亮
 * @since 2020/11/25
 */
@Controller
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @Autowired
    UserVoService userVoService;

    @GetMapping("/login")
    public String loginGet(Model model, String next) {
        model.addAttribute("next", next != null ? next : "/");
        model.addAttribute("tip", "请先登录！！！");
        return "user/login";
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, Model model, String username, String password, String next) {
        model.addAttribute("next", next != null ? next : "/");
        if (username == null || password == null) {
            model.addAttribute("tip", "用户名或密码不能为空！！！");
            return "user/login";
        }
        AuthUser authUser = authUserService.login(username, password);
        if (authUser == null) {
            model.addAttribute("tip", "用户名或密码错误！！！");
            return "user/login";
        }
        session.setAttribute("user", authUser);
        if (next != null) {
            return "redirect:" + next;
        }
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String registerGet(Model model, String next) {
        model.addAttribute("next", next != null ? next : "/");
        model.addAttribute("tip", null);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(HttpSession session, Model model, String username, String password, String password2, String next) {
        model.addAttribute("next", next != null ? next : "/");

        if (username == null || password == null || password2 == null) {
            model.addAttribute("tip", "用户名或密码不能为空！！！");
            return "user/register";
        }
        if (!password.equals(password2)) {
            model.addAttribute("tip", "密码前后不一致！！！");
            return "user/register";
        }
        if (authUserService.isExisted(username)) {
            model.addAttribute("tip", "该账号已存在！！！");
            return "user/register";
        }
        AuthUser authUser = authUserService.register(username, password);
        if (authUser == null) {
            model.addAttribute("tip", "注册失败！！！");
            return "user/register";
        }
        session.setAttribute("user", authUser);
        if (next != null) {
            return "redirect:" + next;
        }
        return "redirect:/index";
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public UserVO getUserInfo(HttpSession session) {
        AuthUser authUser = (AuthUser) session.getAttribute("user");
        if (authUser == null) {
            return new UserVO(-1, "未登录", 0.00);
        }
        UserVO userVO = userVoService.getUserVO(authUser.getUsername());
        if (userVO == null) {
            return new UserVO(-1, "未登录", 0.00);
        }
        return userVO;
    }

    @PostMapping(value = "/recharge")
    @ResponseBody
    public RechargeVO recharge(HttpSession session, double amount, String next) {
        AuthUser authUser = (AuthUser) session.getAttribute("user");
        RechargeVO rechargeVO = userVoService.recharge(authUser.getUsername(), amount);
        rechargeVO.setNext(next);
        return rechargeVO;
    }


    @PostMapping("/rePassword")
    @ResponseBody
    public Map<String, Object> rePassword(HttpSession session, String oldPassword, String password1, String password2) {
        Map<String, Object> map = new HashMap<>(4);
        if (oldPassword == null || password1 == null || password2 == null) {
            map.put("status", -1);
            map.put("error", "密码不能为空");
            return map;
        }
        if (!password1.equals(password2)) {
            map.put("status", -1);
            map.put("error", "新密码前后不一致");
            return map;
        }
        if (oldPassword.equals(password1)) {
            map.put("status", -1);
            map.put("error", "新密码不能跟旧密码相同");
            return map;
        }

        AuthUser user = (AuthUser) session.getAttribute("user");
        if (!AuthenticateUtils.checkPassword(oldPassword, user.getPassword())) {
            map.put("status", -1);
            map.put("error", "旧密码错误");
            return map;
        }
        AuthUser user1 = authUserService.rePassword(user, password1);
        if (user1 == null) {
            map.put("status", -1);
            map.put("msg", "密码修改失败");
            return map;
        }
        user = user1;
        session.setAttribute("user", user);
        map.put("status", 200);
        map.put("msg", "密码修改成功");
        return map;
    }


    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, String next) {
        session.invalidate();
        if (next != null) {
            return "redirect:/login?next=" + next;
        }
        return "redirect:/login";
    }
}
