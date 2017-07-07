package com.feelcolor.website.service;

import com.alipay.api.AlipayApiException;

public interface AliPayService {

    /**
     * 
     * @param tradeNo 交易编号
     * @param totalAmount 付款金额
     * @param orderName 订单名称
     * @param description 描述
     * @return
     * @throws AlipayApiException
     */
    String pay(String tradeNo, String totalAmount, String orderName, String description) throws AlipayApiException;

}
