package com.feelcolor.website.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.feelcolor.utils.DateUtil;

@RestController
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    
    @RequestMapping("/test")
    public String test(Model model){
        Locale locale = LocaleContextHolder.getLocale();
        //后台获取国际化值方式
        model.addAttribute("hello", messageSource.getMessage("hello", null, locale));
        return "index";
    }
    
    @RequestMapping("/test2")
    @ResponseBody
    public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //实例化一个thymeleaf文本上下文
          WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
    //将前端登陆表单页面提交过来的数据封装到thymeleaf上下文中
          ctx.setVariable("hello", "11111");
          //调用模板引擎转向到对应的模板页面--desc模板页面
          springTemplateEngine.process("index", ctx, response.getWriter());
        return "";
    }
}
