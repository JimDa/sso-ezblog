package com.ezblog.user.endpoints;


import com.ezblog.user.service.UserService;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/user-management")
public class AdminEndpoint {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    @PreAuthorize("#oauth2.hasScope('read') and hasRole('ROLE_ADMIN')")
    public String queryAllUsers(ModelMap modelMap) {
        List<User> allUsers = userService.queryAll();
        modelMap.addAttribute("allUsers", allUsers);
        return "list";
    }
}
