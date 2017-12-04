package com.feelcolor.website.controller;

import com.feelcolor.website.common.TokenAnnotation;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

    @Resource
    private UserInfoService userInfoService;

    @TokenAnnotation(save=true)
    @RequestMapping("/registerView")
    @ApiOperation(value = "注册", httpMethod = "GET")
    public String registerView(HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("token").toString());
        return "login/register";
    }

    @RequestMapping("/register")
    @ResponseBody
    @TokenAnnotation(remove=true)
    public Map<String,Object> register(UserInfo userInfo){
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        userInfo.setId(uuid);
        userInfoService.inserUser(userInfo);
        return ajaxResult("success",null,"添加成功");
    }
}
