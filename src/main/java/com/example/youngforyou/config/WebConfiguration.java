package com.example.youngforyou.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class WebConfiguration implements WebMvcConfigurer {


    static List<String> pathUrl = new ArrayList<String>();
    static {
        pathUrl.add("/**/login");
        pathUrl.add("/**/signin");
        pathUrl.add("/test/**");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(pathUrl);                //放行路径
        registration.excludePathPatterns(                         //添加不拦截路径
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf"
        );
    }
}
