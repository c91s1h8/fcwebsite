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
    /**
     * 服务与支持
     * @return
     */
    @RequestMapping("/support")
    public String support(){
        return "support";
    }
    
    /**
     * 人才招聘
     * @return
     */
    @RequestMapping("/recruitment")
    public String recruitment(){
        return "recruitment";
    }
}
