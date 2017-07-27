package com.feelcolor.website.controller;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("user")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/getUserById")
    @ApiOperation(value = "通过id查询用户", httpMethod = "POST")
    public @ResponseBody UserInfo getUserById(String id){
        log.info("cccccccccccccccccccccccccccccccccccccccccccccccc");
        log.info("ddddddddddddddddddddddddddddddddddddddddddddddddd");
        return userInfoService.selectByPrimaryKey(id);
    }
    
    @RequestMapping("/test")
    @ApiOperation(value = "test", httpMethod = "GET")
    public @ResponseBody String test()throws RuntimeException{
        userInfoService.test();
        return "";
    }
    
   
}
