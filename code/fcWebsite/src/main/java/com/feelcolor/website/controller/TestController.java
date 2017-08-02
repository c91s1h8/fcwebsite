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

    public static void main(String[] args) {
        // lambda表达式结构
        // (Type param1, param2, .....) -> {
        // statment1;
        // statment2;
        // ....
        // return statmentM;
        // }

        List<Integer> integerList = Arrays.asList(5, 2, 4, 3, 1, 6, 7, 1, 3, 5);
        List<String> stringList = Arrays.asList("a", "asds", ".net", "java", "hello java", "javalambda", "ccc");
        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(new UserInfo("1", "Mahesh", 17));
        userList.add(new UserInfo("2", "Suresh", 13));
        userList.add(new UserInfo("3", "Nilesh", 15));
        userList.add(new UserInfo("4", "csh", 15));

        // ====================================================================================================================================
        // double[] myArray = new double[100000];
        // for (int i = 0; i < 100000; i++) {
        // try {
        // myArray[i] = SecureRandom.getInstanceStrong().nextDouble();
        // } catch (NoSuchAlgorithmException e) {
        // e.printStackTrace();
        // }
        // }
        // Arrays.parallelSort(myArray);
        // Stream.of(myArray).forEach(System.out::println);

    }

}
