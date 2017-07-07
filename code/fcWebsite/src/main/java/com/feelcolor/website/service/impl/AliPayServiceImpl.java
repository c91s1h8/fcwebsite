package com.feelcolor.website.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.feelcolor.website.config.AlipayConfig;
import com.feelcolor.website.service.AliPayService;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Resource
    AlipayClient alipayClient;
    
    @Override
    public String pay(String tradeNo, String totalAmount, String orderName, String description) throws AlipayApiException {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ tradeNo +"\"," 
                + "\"total_amount\":\""+ totalAmount +"\"," 
                + "\"subject\":\""+ orderName +"\"," 
                + "\"body\":\""+ description +"\"," 
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

}
