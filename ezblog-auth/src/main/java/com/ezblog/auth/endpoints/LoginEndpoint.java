package com.ezblog.auth.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginEndpoint {
    @GetMapping("/login")
    public String login() {
        return "page-login";
    }
}
