package com.joy.NotificationService.util;

import com.joy.NotificationService.model.response.ErrorResponse;
import com.joy.NotificationService.util.exceptions.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

    @Value("${auth-pwd}")
    private String pwd;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ForbiddenException {
        String header= request.getHeader("auth-token");
        if(header.equals(pwd))
            return true;
        throw new ForbiddenException("Authentication failed");
    }
}
