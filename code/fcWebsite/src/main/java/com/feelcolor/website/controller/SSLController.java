package com.feelcolor.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ssl")
public class SSLController {

    @RequestMapping("/ssltest")
    public String ssltest(){
        return "ok,ssl";
    }
}
