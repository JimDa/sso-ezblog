package com.ezblog.auth.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author dpc
 * 自定义授权失败处理器
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Locale currentLocale = Locale.getDefault();
        String errMessage;
        String message = messageSource.getMessage("message.badCredentials", null, currentLocale);
        if (e.getMessage().equalsIgnoreCase("blocked")) {
            errMessage = messageSource.getMessage("auth.message.blocked", null, currentLocale);
        } else {
            errMessage = e.getMessage();
        }
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        System.out.println(String.format("访问失败！报错信息为：%s，报错时间为：%s", errMessage, LocalDateTime.now().toString()));
    }
}
