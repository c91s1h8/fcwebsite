package com.feelcolor.website.controller.wx;

import com.feelcolor.website.common.wx.SignUtil;
import com.feelcolor.website.service.wx.WxCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wxCore")
@Slf4j
public class CoreController {
    @Resource
    private WxCoreService wxCoreService;

    @RequestMapping(value = "/coreService",method = RequestMethod.GET)
    public String coreService(@RequestParam(name = "signature" ,required = false) String signature  ,
                                 @RequestParam(name = "nonce",required = false) String  nonce ,
                                 @RequestParam(name = "timestamp",required = false) String  timestamp ,
                                 @RequestParam(name = "echostr",required = false) String  echostr){
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            log.info("接入成功");
            return echostr;
        }
        log.error("接入失败");
        return "";
    }

    @RequestMapping(value = "/coreService",method = RequestMethod.POST)
    public String coreService(HttpServletRequest request){
        String respMessage = wxCoreService.processRequest(request);
        log.info("wxCore返回消息："+respMessage);
        return respMessage;
    }

}
