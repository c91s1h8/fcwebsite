package com.feelcolor.website.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feelcolor.website.common.TokenAnnotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
         if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            TokenAnnotation tokenAnnotation = method.getMethodAnnotation(TokenAnnotation.class);
            if (tokenAnnotation != null) {
                boolean needSave = tokenAnnotation.save();
                if (needSave) {
                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemove = tokenAnnotation.remove();
                if (needRemove) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    } else {
                        request.getSession(false).removeAttribute("token");
                    }
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter("token");
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }

}
