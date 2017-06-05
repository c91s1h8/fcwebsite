package com.feelcolor.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
