package com.src.config.interceptor;

import com.src.interceptor.AuthHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        // 增加鉴权拦截器
        registry.addInterceptor(new AuthHandlerInterceptor(getClass().getName())).excludePathPatterns("/s/*");
    }
}
