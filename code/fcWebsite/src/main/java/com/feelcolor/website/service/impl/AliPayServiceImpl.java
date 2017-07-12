package com.feelcolor.website.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.feelcolor.utils.DateUtil;
import com.feelcolor.website.config.AlipayConfig;
import com.feelcolor.website.dao.mapper.AlipayNotifyRecordMapper;
import com.feelcolor.website.model.po.AlipayNotifyRecord;
import com.feelcolor.website.service.AliPayService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Resource
    AlipayClient alipayClient;

    @Resource
    AlipayNotifyRecordMapper alipayNotifyRecordMapper;

    @Override
    public String pay(String orderNo, String totalAmount, String orderName, String description)
            throws AlipayApiException {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNo + "\"," + "\"total_amount\":\"" + totalAmount
                + "\"," + "\"subject\":\"" + orderName + "\"," + "\"body\":\"" + description + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    @Transactional
    public String processNofifyRecord(Map<String, String> params) throws AlipayApiException, ParseException {

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
                AlipayConfig.sign_type); // 调用SDK验证签名

        if (signVerified) {// 验证成功
            // 商户订单号
            String out_trade_no = params.get("out_trade_no");

            // 支付宝交易号
            String trade_no = params.get("trade_no");

            // 交易状态
            String trade_status = params.get("trade_status");
            log.info("==================================================================================="
                    + trade_status + "|" + out_trade_no + "|" + trade_no);

            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 付款完成后，支付宝系统发送该交易状态通知
            }
            insertNofifyRecord(params);
            return "success";

        } else {// 验证失败
            return "fail";

            // 调试用，写文本函数记录程序运行情况是否正常
            // String sWord = AlipaySignature.getSignCheckContentV1(params);
            // AlipayConfig.logResult(sWord);
        }

    }

    @Override
    public String tradeQuery(String orderNo, String tradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNo + "\"," + "\"trade_no\":\"" + tradeNo + "\"}");
        return alipayClient.execute(alipayRequest).getBody();
    }

    @Transactional
    public void insertNofifyRecord(Map<String, String> params) throws ParseException {
        AlipayNotifyRecord record = new AlipayNotifyRecord();
        if(params.get("gmt_create")!=null){
            record.setGmtCreate(DateUtil.parse(params.get("gmt_create").toString()));
        }
        if(params.get("charset")!=null){
            record.setCharset(params.get("charset").toString());
        }
        if(params.get("gmt_payment")!=null){
            record.setGmtPayment(DateUtil.parse(params.get("gmt_payment").toString()));
        }
        if(params.get("notify_time")!=null){
            record.setNotifyTime(DateUtil.parse(params.get("notify_time").toString()));
        }
        if(params.get("subject")!=null){
            record.setSubject(params.get("subject").toString());
        }
        if(params.get("sign")!=null){
            record.setSign(params.get("sign").toString());
        }
        if(params.get("buyer_id")!=null){
            record.setBuyerId(params.get("buyer_id").toString());
        }
        if(params.get("body")!=null){
            record.setBody(params.get("body").toString());
        }
        if(params.get("invoice_amount")!=null){
            record.setInvoiceAmount(new BigDecimal(params.get("invoice_amount").toString()));
        }
        if(params.get("version")!=null){
            record.setVersion(params.get("version").toString());
        }
        if(params.get("notify_id")!=null){
            record.setNotifyId(params.get("notify_id").toString());
        }
        if(params.get("fund_bill_list")!=null){
            record.setFundBillList(params.get("fund_bill_list").toString());
        }
        if(params.get("notify_type")!=null){
            record.setNotifyType(params.get("notify_type").toString());
        }
        if(params.get("out_trade_no")!=null){
            record.setOutTradeNo(params.get("out_trade_no").toString());
        }
        if(params.get("total_amount")!=null){
            record.setTotalAmount(new BigDecimal(params.get("total_amount").toString()));
        }
        if(params.get("trade_status")!=null){
            record.setTradeStatus(params.get("trade_status").toString());
        }
        if(params.get("trade_no")!=null){
            record.setTradeNo(params.get("trade_no").toString());
        }
        if(params.get("auth_app_id")!=null){
            record.setAuthAppId(params.get("auth_app_id").toString());
        }
        if(params.get("receipt_amount")!=null){
            record.setReceiptAmount(new BigDecimal(params.get("receipt_amount").toString()));
        }
        if(params.get("point_amount")!=null){
            record.setPointAmount(new BigDecimal(params.get("point_amount").toString()));
        }
        if(params.get("app_id")!=null){
            record.setAppId(params.get("app_id").toString());
        }
        if(params.get("buyer_pay_amount")!=null){
            record.setBuyerPayAmount(new BigDecimal(params.get("buyer_pay_amount").toString()));
        }
        if(params.get("sign_type")!=null){
            record.setSignType(params.get("sign_type").toString());
        }
        if(params.get("seller_id")!=null){
            record.setSellerId(params.get("seller_id").toString());
        }
        if(params.get("out_biz_no")!=null){
            record.setOutBizNo(params.get("out_biz_no").toString());
        }
        if(params.get("refund_fee")!=null){
            record.setRefundFee(new BigDecimal(params.get("refund_fee").toString()));
        }
        if(params.get("out_biz_no")!=null){
            record.setOutBizNo(params.get("out_biz_no").toString());
        }
        if(params.get("gmt_refund")!=null){
            record.setGmtRefund(DateUtil.parse(params.get("gmt_refund").toString()));
        }
        if(params.get("gmt_close")!=null){
            record.setGmtClose(DateUtil.parse(params.get("gmt_close").toString()));
        }
        if(params.get("voucher_detail_list")!=null){
            record.setVoucherDetailList(params.get("voucher_detail_list").toString());
        }
        if(params.get("passback_params")!=null){
            record.setPassbackParams(params.get("passback_params").toString());
        }
        record.setCreateTime(new Date());
        alipayNotifyRecordMapper.insertSelective(record);
    }

}
