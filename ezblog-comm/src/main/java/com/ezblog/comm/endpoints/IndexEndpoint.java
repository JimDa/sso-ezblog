package com.ezblog.comm.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexEndpoint {
    @GetMapping("/list")
    public String index(ModelMap modelMap) {
        return "index";
    }
}
