package com.ezblog.comm.endpoints;


import com.ezblog.comm.feign.clients.RevokeTokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutEndpoint {
    @Autowired
    private RevokeTokenClient revokeTokenClient;

    @GetMapping(value = "/custom/logout")
    public String logout(HttpServletRequest servletRequest) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        revokeTokenClient.revoke(details.getTokenValue(), userName);
        return "redirect:http://localhost:8081/auth-service/login";
    }
}
