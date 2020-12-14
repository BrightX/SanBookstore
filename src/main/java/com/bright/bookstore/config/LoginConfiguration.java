package com.bright.bookstore.config;

import com.bright.bookstore.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 徐亮亮
 * @since 2020/11/28
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置用户登录拦截器 指定拦截的资源
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/login", "/register", "/index", "/", "/favicon.ico", "/getUserInfo");
    }
}
