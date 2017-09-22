package com.feelcolor.website.aspect;

import com.feelcolor.website.common.AuthAnnotation;
import com.feelcolor.website.common.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class AuthAspect {

    @Before("execution(* com.feelcolor.website.controller.TestController.test6(..))")
    public void before(JoinPoint joinPoint) throws Exception {

        MethodSignature  methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AuthAnnotation isLoggedAnnotation = method.getAnnotation(AuthAnnotation.class);
        if (isLoggedAnnotation != null) {
            System.out.println("权限切入=====================================================================");
            boolean isLogged = isLoggedAnnotation.isLogged();
            if (isLogged) {
                throw new AuthException("权限不够");
            } else {

            }
        } else {

        }

    }
}
