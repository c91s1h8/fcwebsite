package com.feelcolor.website.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/test")
    public String test(){
        System.out.println(LocaleContextHolder.getLocale());
        return "index";
    }
}
