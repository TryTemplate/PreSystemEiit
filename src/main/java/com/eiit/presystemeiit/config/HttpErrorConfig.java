package com.eiit.presystemeiit.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Ahtuor liujingguang
 * @date 2022/8/12 16:55
 * @description 网络请求错误页面跳转
 */

@Configuration
public class HttpErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        //定义错误配置
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");

        //将错误配置注册进监听器
        registry.addErrorPages(error404Page);
    }
}
