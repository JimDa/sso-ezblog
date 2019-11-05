package com.ezblog.auth.endpoints;

import com.ezblog.auth.service.IUserAccountService;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal")
public class PersonalUserEndpoint {
    @Autowired
    private IUserAccountService iUserAccountService;

    @GetMapping("/info/{userId}")
    public String personalUserInfo(@PathVariable("userId") String userId, ModelMap modelMap) {
        User user = iUserAccountService.loadUserById(userId);
        modelMap.addAttribute("userInfo", user);
        return "user-profile";
    }
}
