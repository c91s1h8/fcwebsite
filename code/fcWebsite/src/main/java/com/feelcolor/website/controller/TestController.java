package com.feelcolor.website.controller;

import com.feelcolor.website.model.po.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RestController
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public TestController() {
    }

    @RequestMapping("/test")
    @ApiOperation(value = "", httpMethod = "GET", notes = "测试国际化")
    public String test(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        // 后台获取国际化值方式
        model.addAttribute("hello", messageSource.getMessage("hello", null, locale));
        return "index";
    }

    @RequestMapping("/test2")
    @ResponseBody
    @ApiOperation(value = "", httpMethod = "POST", notes = "测试模板")
    public String test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 实例化一个thymeleaf文本上下文
        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
        // 将前端登陆表单页面提交过来的数据封装到thymeleaf上下文中
        ctx.setVariable("hello", "11111");
        // 调用模板引擎转向到对应的模板页面--desc模板页面
        springTemplateEngine.process("index", ctx, response.getWriter());
        return "";
    }

    @ApiOperation("测试Redis")
    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    @ResponseBody
    public String test3(String str) {
        redisTemplate.delete(str);
        redisTemplate.opsForValue().set(str, str);
        return redisTemplate.opsForValue().get(str);
    }

    @ApiOperation("测试ThreadPool")
    @RequestMapping(value = "/test4", method = RequestMethod.POST)
    @ResponseBody
    public void test4(){
        for (int i =0;i<200;i++){
            int finalI = i;
            threadPoolTaskExecutor.execute(new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("============"+ finalI);
            }));
        }

    }

    @ApiOperation("测试ThreadPool")
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @ResponseBody
    public String test5(){
     if(Math.random()>0.1){
         return "没抢到 哈哈哈";
     }else{
         return "可算抢到了";
     }

    }

    public static void main(String[] args) throws ParseException {
    String[] a ={"1","2"};

    }



}
