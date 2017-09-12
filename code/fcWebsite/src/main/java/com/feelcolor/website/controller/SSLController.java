package com.feelcolor.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
@RequestMapping("ssl")
public class SSLController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/ssltest")
    @ResponseBody
    public String ssltest(){
        return "ok,ssl";
    }

    @RequestMapping("/sslpost")
    @ResponseBody
    public String sslpost(){
        String rs = restTemplate.postForObject("",null,String.class);
        return rs;
    }


}
