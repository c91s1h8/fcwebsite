package com.feelcolor.website.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.feelcolor.website.model.po.AlipayNotifyRecord;
import com.feelcolor.website.model.vo.AlipayNotifyRecordVo;
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
    public String pay(String orderNo,String totalAmount,String orderName,String description) throws AlipayApiException{
        return aliPayService.pay(orderNo,totalAmount,orderName,description);
    }
    
    @RequestMapping("/notify")
    @ResponseBody
    public String notify(AlipayNotifyRecordVo record,HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException{
        log.info("===================支付宝回调======================");
        
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
       return aliPayService.insertNofifyRecord(params,record);
    }
    
    @RequestMapping("/tradeQuery")
    @ResponseBody
    public String tradeQuery(String orderNo,String tradeNo) throws AlipayApiException{
        return aliPayService.tradeQuery(orderNo,tradeNo);
    }
}
