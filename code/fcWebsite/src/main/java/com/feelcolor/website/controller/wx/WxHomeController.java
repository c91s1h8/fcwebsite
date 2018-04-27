package com.feelcolor.website.controller.wx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx")
public class WxHomeController {

    @RequestMapping("/home")
    public String home() {
        return "/wx/home";
    }
}
