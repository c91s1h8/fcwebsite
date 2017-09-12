package com.feelcolor.website.controller;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserInfoController extends BaseController{
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/getUserById")
    @ApiOperation(value = "通过id查询用户", httpMethod = "POST")
    public @ResponseBody UserInfo getUserById(String id) {
        return userInfoService.getUserById(id);
    }

    @RequestMapping("/getUserList")
    @ApiOperation(value = "查询所有用户", httpMethod = "POST")
    public @ResponseBody PageImpl<UserInfo> getUserList(Integer id, String name, Integer age, Pageable pageable) {
        return userInfoService.getUserList(id, name, age, pageable);
    }

    @RequestMapping("/insert")
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    public @ResponseBody int getUserById(UserInfo user) {
        return userInfoService.inserUser(user);
    }

    @RequestMapping("/test")
    @ApiOperation(value = "test", httpMethod = "GET")
    public @ResponseBody String test() throws RuntimeException {
        return "";
    }

}
