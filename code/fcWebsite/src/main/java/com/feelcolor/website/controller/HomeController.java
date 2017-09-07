package com.feelcolor.website.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    @ApiOperation(value = "首页", httpMethod = "GET")
    public String home() {
        return "home";
    }

    /**
     * 服务与支持
     *
     * @return
     */
    @RequestMapping("/support")
    @ApiOperation(value = "服务与支持", httpMethod = "GET")
    public String support() {
        return "support/support";
    }

    /**
     * 人才招聘
     *
     * @return
     */
    @RequestMapping("/recruitment")
    @ApiOperation(value = "人才招聘", httpMethod = "GET")
    public String recruitment() {
        return "recruitment/recruitment";
    }

    /**
     * 企业简介
     *
     * @return
     */
    @RequestMapping("/companyProfile")
    @ApiOperation(value = "企业简介", httpMethod = "GET")
    public String companyProfile() {
        return "companyProfile/companyProfile";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "GET")
    public String login() {
        return "login/login";
    }

}
