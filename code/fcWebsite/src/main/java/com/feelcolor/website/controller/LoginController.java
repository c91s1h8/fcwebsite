package com.feelcolor.website.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("/register")
    @ApiOperation(value = "注册", httpMethod = "GET")
    public String register() {
        return "login/register";
    }
}
