package com.joy.NotificationService.util;

import com.joy.NotificationService.model.response.ErrorResponse;
import com.joy.NotificationService.util.exceptions.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ForbiddenException {
        String header= request.getHeader("auth-token");
//        log.info(header);
        if(header.equals("pass"))
            return true;
        throw new ForbiddenException("Authentication failed");
    }
}
