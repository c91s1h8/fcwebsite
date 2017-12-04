package com.feelcolor.website.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.classic.Logger;

@Controller
@Slf4j
public class HomeController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    @ApiOperation(value = "首页", httpMethod = "GET")
    public String home() {
        log.info("{\"a\":\"aaaaa\",\"b\":\"bbbbbbb\"}");
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
        log.info("fclog：进入服务与支持");
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
        log.info("fclog：进入人才招聘");
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
        log.info("fc2log：企业简介");
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
        log.info("访问登录");
        return "login/login";
    }

}
