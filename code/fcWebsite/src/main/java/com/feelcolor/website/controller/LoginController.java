package com.feelcolor.website.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.feelcolor.website.common.TokenAnnotation;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.service.UserInfoService;
import com.feelcolor.website.task.AsyncTask;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
    @Resource
    private AsyncTask asyncTask;

    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private RestTemplate restTemplate;

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
    public Map<String,Object> register(UserInfo userInfo) throws InterruptedException, ExecutionException{
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        userInfo.setId(uuid);
        userInfoService.inserUser(userInfo);
        return ajaxResult("success",null,"添加成功");
    }
    
  
}
