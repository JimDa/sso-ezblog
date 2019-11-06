package com.ezblog.user.endpoints;

import com.ezblog.user.service.UserService;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userName}")
    public String getUserDetails(@PathVariable("userName") String userName, ModelMap modelMap) {
        User user = userService.queryUserByName(userName);
        modelMap.addAttribute("user", user);
        return "page-single_settings";
    }
}
