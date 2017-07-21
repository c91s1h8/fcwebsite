package com.feelcolor.website.controller;

import static org.mockito.Matchers.startsWith;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Thread;
import org.aspectj.weaver.bcel.Utility;
import org.assertj.core.internal.Strings;
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
import com.feelcolor.website.model.po.UserInfo;

@RestController
public class TestController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @RequestMapping("/test")
    public String test(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        // 后台获取国际化值方式
        model.addAttribute("hello", messageSource.getMessage("hello", null, locale));
        return "index";
    }

    @RequestMapping("/test2")
    @ResponseBody
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



    public static void main(String[] args) {
//        lambda表达式结构
//        (Type param1, param2, .....) -> {
//            statment1;
//            statment2;
//            ....
//            return statmentM;
//        }
        
        List<Integer> integerList = Arrays.asList(5, 2, 4, 3, 1, 6, 7, 1, 3, 5);
        List<String> stringList = Arrays.asList("a", "asds", ".net", "java", "hello java", "javalambda", "ccc");
        List<UserInfo> userList = new ArrayList<UserInfo>();
        userList.add(new UserInfo("1", "Mahesh", 17));
        userList.add(new UserInfo("2", "Suresh", 13));
        userList.add(new UserInfo("3", "Nilesh", 15));
        userList.add(new UserInfo("4", "csh", 15));
        
//        分组
//        Map<Integer, List<UserInfo>> groupList = userList.stream().collect(Collectors.groupingBy(UserInfo::getAge));
//        分区
//        Map<Boolean,List<UserInfo>> partitionList = userList.stream().collect(Collectors.partitioningBy(n -> n.getAge()>=15));
        
//        sorted reversed
//        userList.stream().sorted(Comparator.comparing(UserInfo::getAge).reversed()).collect(Collectors.toList()).forEach(n ->System.out.println(n.getId()));
        
//        合并
//        Stream.concat(Stream.of(1,3,2), Stream.of(4,5,6)).forEach(n -> System.out.println(n));
        
//        循环输出
//        integerList.forEach(n -> System.out.println(n));
//        获取大于3的第一个数据
//        Optional<Integer> i = integerList.stream().filter(n -> (n>3)).findFirst();
//        System.out.println(i.get());
//        获取包含java的数据 形成新的list
//        List<String> list = stringList.stream().filter(n -> n.contains("java")).collect(Collectors.toList());
//        list.forEach(n -> System.out.println(n));
//        排序
//        integerList.sort((e1, e2) -> e1.compareTo(e2));
//        map修改值 * 2
//        integerList.stream().map(n -> n*2).forEach(System.out::println);
//        reduce进行统计
//        System.out.println(integerList.stream().map(cost -> cost + cost*1).reduce((sum,cost)->sum+cost).get()); 
        
//        List<Integer> distinctLsit = integerList.stream().distinct().collect(Collectors.toList());
//        distinctLsit.forEach(System.out::println);
        
//        IntSummaryStatistics stats = integerList.stream().mapToInt((x) -> x).summaryStatistics();
//        System.out.println(stats.getMax()+"==="+stats.getAverage()+"==="+stats.getCount());
         
//        integerList.stream().peek((n) -> {System.out.println("accept:" + n);}).forEach(System.out::println);
        
//        integerList.stream().skip(2).limit(4).forEach(System.out::println);
        
//        filter(stringList,(str) -> ((String) str).contains("java"));
        
//        boolean b = integerList.stream().allMatch(n -> n>=1);
//        boolean c = integerList.stream().anyMatch(n -> n>=1);
//        System.out.println(b);
        
        
        
//        ====================================================================================================================================
        double[] myArray = new double[100000];  
        for (int i = 0; i < 100000; i++) {
            try {
                myArray[i]=SecureRandom.getInstanceStrong().nextDouble();
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Arrays.parallelSort(myArray);
        Stream.of(myArray).forEach(System.out::println);
        
    }

    public static void filter(List<String> names, Predicate condition) {
        names.stream().filter(str -> condition.test(str)).forEach(n -> System.out.println(n));
    }
    


}
