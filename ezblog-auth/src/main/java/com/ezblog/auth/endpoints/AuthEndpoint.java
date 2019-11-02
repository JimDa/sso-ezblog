package com.ezblog.auth.endpoints;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthEndpoint {
    @RequestMapping("/oauth/exit")
    public void exit(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        new SecurityContextLogoutHandler().logout(httpServletRequest, null, null);
    }
}
