package com.feelcolor.website.controller;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

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

}
