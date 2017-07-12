package com.feelcolor.website.service;

import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.feelcolor.website.model.po.AlipayNotifyRecord;
import com.feelcolor.website.model.vo.AlipayNotifyRecordVo;

public interface AliPayService {

    /**
     * 
     * @param tradeNo
     *            交易编号
     * @param totalAmount
     *            付款金额
     * @param orderName
     *            订单名称
     * @param description
     *            描述
     * @return
     * @throws AlipayApiException
     */
    String pay(String tradeNo, String totalAmount, String orderName, String description) throws AlipayApiException;

    /**
     * 插入支付宝回调记录
     * @param params 
     * 
     * @param record
     * @throws AlipayApiException 
     */
    String insertNofifyRecord(Map<String, String> params, AlipayNotifyRecordVo record) throws AlipayApiException;

    /**
     * 交易查询
     * 
     * @param orderNo
     * @param tradeNo
     * @return
     * @throws AlipayApiException 
     */
    String tradeQuery(String orderNo, String tradeNo) throws AlipayApiException;


}
