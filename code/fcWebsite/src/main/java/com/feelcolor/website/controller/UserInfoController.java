package com.feelcolor.website.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;

@Controller
@RequestMapping("user")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/getUserById")
    public @ResponseBody UserInfo getUserById(String id){
        return userInfoService.selectByPrimaryKey(id);
    }
}
