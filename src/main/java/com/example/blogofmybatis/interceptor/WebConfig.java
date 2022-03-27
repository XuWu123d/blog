package com.example.blogofmybatis.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//拦截器
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) //对LoginInterceptor()登录进行拦截
                .addPathPatterns("/admin/**")  //拦截的路径
                .excludePathPatterns("/admin")  //放行的路径
                .excludePathPatterns("/admin/login");
    }
}
