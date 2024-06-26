package com.example.mycnblog.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置拦截器
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    // 不拦截的 url 集合
    List<String> excludes = new ArrayList<String>(){{
        add("/**/*.html");
        add("/js/**"); // 放行 static/js 下的所有文件
        add("/editor.md/**"); // 放行 static/editor.md 下的所有文件
        add("/css/**"); // 放行 static/css 下的所有文件
        add("/img/**"); // 放行 static/img 下的所有文件
        add("/user/reg"); // 放行注册接口
        add("/user/login"); // 放行登录接口
        add("/art/detail"); // 放行文章详情接口
        add("/art/list"); // 放行文章分页列表接口
        add("/art/totalpage"); // 放行文章分页总页数接口
    }};

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        InterceptorRegistration registration =
                registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**"); // 拦截所有请求
        registration.excludePathPatterns(excludes); // 排除不拦截的 url
    }
}
