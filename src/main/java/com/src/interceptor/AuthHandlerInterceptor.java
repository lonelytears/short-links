package com.src.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import com.src.Application;
import com.src.config.exception.BusinessException;
import com.src.enums.ExceptionEnum;
import com.src.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthHandlerInterceptor implements HandlerInterceptor {
    private String name;

    public AuthHandlerInterceptor(String name) {
        this.name = name == null ? getClass().getName() : name;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("enter {} interceptor pre handle method at {}", name, System.currentTimeMillis());
        IAuthenticationService authenticationService = SpringUtil.getBean(IAuthenticationService.class);
        if (Boolean.FALSE.equals(authenticationService.check(request.getHeader("app_id"), request.getHeader("app_secret")))) {
            throw new BusinessException(ExceptionEnum.NOT_ACCESS.getResultMsg(),ExceptionEnum.NOT_ACCESS.getResultCode());
        }

        return true;
    }

}
