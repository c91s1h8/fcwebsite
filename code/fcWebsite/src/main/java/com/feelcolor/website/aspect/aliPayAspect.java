package com.feelcolor.website.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class aliPayAspect {

    @Before("execution(* com.feelcolor.website.service.impl.AliPayServiceImpl.pay(..))")
    public void beforePayLog(JoinPoint point) {
        String orderNo = (String) point.getArgs()[0];
        String totalAmount = (String) point.getArgs()[1];
        String orderName = (String) point.getArgs()[2];
        String description = (String) point.getArgs()[3];
        log.info("======================================正在付款===============================================");
        log.info("=============" + orderNo + "--" + totalAmount + "--" + orderName + "--" + description);
    }

    @After("execution(* com.feelcolor.website.service.impl.AliPayServiceImpl.pay(..))")
    public void afterPayLog(JoinPoint point) {
        String orderNo = (String) point.getArgs()[0];
        String totalAmount = (String) point.getArgs()[1];
        String orderName = (String) point.getArgs()[2];
        String description = (String) point.getArgs()[3];
        log.info("======================================付款完成===============================================");
        log.info("=============" + orderNo + "--" + totalAmount + "--" + orderName + "--" + description);
    }
}
