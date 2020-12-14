package com.bright.bookstore.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 徐亮亮
 * @since 2020/11/28
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行之前 执行
     * 返回 true 放行
     * 返回 false 不放行
     * 用户登录拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户是否登录
        boolean isNotLogin = request.getSession().getAttribute("user") == null;
        if (isNotLogin) {
            response.sendRedirect(request.getContextPath() + "/login?next=" + request.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 视图返回之前 执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 整个流程都执行之后 执行
    }
}
