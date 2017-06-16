package com.feelcolor.website.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RequestInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();

        StringBuilder sb = new StringBuilder("\n");
        sb.append("↓↓↓↓↓↓↓↓↓↓↓↓请求↓↓↓↓↓↓↓↓↓↓↓↓\n");
        sb.append(nameValueFormat("URI", request.getRequestURI()));
        if (handler != null) {
            sb.append(nameValueFormat("Handler", handler.toString()));
        }
        String sessionId = null;
        if (session != null) {
            sb.append("----------Session-----------\n");
            sessionId = session.getId();
            sb.append(nameValueFormat("SessionId", sessionId));
            Enumeration<String> sNames = session.getAttributeNames();
            if (sNames != null) {
                while (sNames.hasMoreElements()) {
                    sb.append(sNames.nextElement());
                    sb.append(" | ");
                }
                sb.append("\n");
            }
        }
        sb.append(nameValueFormat("Http-Method", request.getMethod()));
        sb.append(nameValueFormat("Content-Type", request.getContentType()));
        sb.append("------------参数-------------\n");
        Map<String, String[]> map = request.getParameterMap();
        if (map == null) {
            sb.append("没有参数\n");
        } else {
            map.forEach((k, v) -> {
                sb.append(nameValueFormat(k, Arrays.asList(v).toString()));
            });
        }
        sb.append("----------Headers----------\n");
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            List<String> valueList = new ArrayList<>();
            while (values.hasMoreElements()) {
                valueList.add(values.nextElement());
            }
            sb.append(nameValueFormat(name, valueList.size() > 0 ? valueList.get(0) : ""));
        }
        sb.append("↑↑↑↑↑↑↑↑↑↑↑↑请求↑↑↑↑↑↑↑↑↑↑↑↑");
        logger.info(sb.toString());
        return true;
    }

    private String nameValueFormat(String name, String value) {
        return String.format("%-15s：%s\n", name, value);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("↓↓↓↓↓↓↓↓↓↓↓↓响应↓↓↓↓↓↓↓↓↓↓↓↓\n");
        sb.append(nameValueFormat("Status", response.getStatus() + ""));
        sb.append(nameValueFormat("Content-Type", response.getContentType()));
        if (handler != null) {
            sb.append(nameValueFormat("Handler", handler.toString()));
        }
        sb.append("----------Headers----------\n");
        Iterator<String> names = response.getHeaderNames().iterator();
        List<String> list = new ArrayList<>();
        while (names.hasNext()) {
            String name = names.next();
            Collection<String> hs = response.getHeaders(name);
            if (!list.contains(name)) {
                list.add(name);
                hs.stream().forEach(value -> {
                    sb.append(nameValueFormat(name, value));
                });
            } else {
                continue;
            }
        }
        sb.append("↑↑↑↑↑↑↑↑↑↑↑↑响应↑↑↑↑↑↑↑↑↑↑↑↑\n");
        logger.info(sb.toString());
    }

}
