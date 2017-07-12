package com.feelcolor.website.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.feelcolor.website.model.po.AlipayNotifyRecord;
import com.feelcolor.website.service.AliPayService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("aliPay")
@Slf4j
public class AliPayController {
    @Resource
    private AliPayService aliPayService;
    
    @RequestMapping("/pay")
    @ResponseBody
    public String pay(String tradeNo,String totalAmount,String orderName,String description) throws AlipayApiException{
        return aliPayService.pay(tradeNo,totalAmount,orderName,description);
    }
    
    @RequestMapping("/notify")
    @ResponseBody
    public void notify(AlipayNotifyRecord record){
        log.info("===================支付宝回调======================");
        aliPayService.insertNofifyRecord(record);
    }
}
