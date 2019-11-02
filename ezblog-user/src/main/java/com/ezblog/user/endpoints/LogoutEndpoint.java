package com.ezblog.user.endpoints;


import com.ezblog.user.feign.RevokeTokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class LogoutEndpoint {
    @Autowired
    private RevokeTokenClient revokeTokenClient;

    @GetMapping(value = "/custom/logout")
    public String logout(HttpServletRequest servletRequest) {
        final Cookie[] cookies = servletRequest.getCookies();
        Arrays.stream(cookies).forEach(cookie -> {
            System.out.println(cookie.getValue());
        });
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        final ResponseEntity<Boolean> responseEntity = revokeTokenClient.revoke(details.getTokenValue());
        System.out.println(String.format("退出是否成功->%s", responseEntity.getBody()));
        return "redirect:http://localhost:8081/auth-service/login";
    }
}
