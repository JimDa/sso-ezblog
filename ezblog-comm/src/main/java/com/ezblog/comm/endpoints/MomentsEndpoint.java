package com.ezblog.comm.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/moments")
public class MomentsEndpoint {
    @GetMapping(value = "/list")
    public String listAllMoments(ModelMap modelMap) {
        ArrayList<String> moments = new ArrayList<String>() {{
            add("第一条");
            add("第二条");
            add("第三条");
            add("第四条");
            add("第五条");
        }};
        modelMap.addAttribute("moments", moments);
        return "momentList";
    }
}
