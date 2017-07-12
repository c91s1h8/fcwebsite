package com.feelcolor.website.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.feelcolor.website.config.AlipayConfig;
import com.feelcolor.website.dao.mapper.AlipayNotifyRecordMapper;
import com.feelcolor.website.model.po.AlipayNotifyRecord;
import com.feelcolor.website.model.vo.AlipayNotifyRecordVo;
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
    public String pay(String orderNo, String totalAmount, String orderName, String description) throws AlipayApiException {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderNo +"\"," 
                + "\"total_amount\":\""+ totalAmount +"\"," 
                + "\"subject\":\""+ orderName +"\"," 
                + "\"body\":\""+ description +"\"," 
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @Override
    public String insertNofifyRecord(Map<String, String> params) throws AlipayApiException {

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = params.get("out_trade_no");
        
            //支付宝交易号
            String trade_no = params.get("trade_no");
        
            //交易状态
            String trade_status = params.get("trade_status");
           log.info("==================================================================================="+trade_status+"|"+out_trade_no+"|"+trade_no);
            
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                    
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
//            record.setCreateTime(new Date());
//            alipayNotifyRecordMapper.insertSelective(record);
            return "success";
            
        }else {//验证失败
            return "fail";
        
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        
        
        
        
        
       
    }

    @Override
    public String tradeQuery(String orderNo, String tradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderNo +"\","+"\"trade_no\":\""+ tradeNo +"\"}");
        return alipayClient.execute(alipayRequest).getBody();
    }

}
