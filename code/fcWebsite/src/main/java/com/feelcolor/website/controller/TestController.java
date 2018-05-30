package com.feelcolor.website.controller;

import com.feelcolor.website.common.AuthAnnotation;
import com.feelcolor.website.model.po.UserInfo;
import com.feelcolor.website.task.AsyncTask;

import com.feelcolor.website.thread.CountThread;
import com.feelcolor.website.thread.SecKillThread;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    private AsyncTask asyncTask;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisTemplate<String, Integer> integerRedisTemplate;

    @Resource
    private RestTemplate restTemplate;

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

    @ApiOperation("----")
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @ResponseBody
    public String test5(){
     if(Math.random()>0.1){
         return "没抢到 哈哈哈";
     }else{
         return "可算抢到了";
     }

    }

    @ApiOperation("测试自定义注解")
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    @ResponseBody
    @AuthAnnotation(isLogged = true)
    public String test6(){
      return "11111111111111111";
    }
    
    
    @ApiOperation("测试Async 异步转同步")
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    @ResponseBody
    public String test7() throws InterruptedException, ExecutionException{
        Future<String> future=asyncTask.returnAsync();
        while(true){
            if(future.isDone()){
                System.out.println("异步请求执行完毕，结果:"+future.get()+"==============================");
                break;
            }
        }
        System.out.println("异步完毕，执行同步请求====================================");
        
      return "11111111111111111";
    }
    
    @ApiOperation("88888")
    @RequestMapping(value = "/test8", method = RequestMethod.GET)
    @ResponseBody
    public String test8(){
        
        Map<String, Object> lxData = restTemplate.postForObject("http://111.203.248.53:10009/Receive/Index", "", Map.class);
        
      return "11111111111111111";
    }


    @ApiOperation("测试ThreadPool")
    @RequestMapping(value = "/test9", method = RequestMethod.POST)
    @ResponseBody
    public void test9(){
        UserInfo userInfo = new UserInfo();
        userInfo.setStatus(100);
//        Thread thread1=new Thread(new CountThread(userInfo),"线程1");
//        Thread thread2=new Thread(new CountThread(userInfo),"线程2");
//        thread1.start();
//        thread2.start();

        if(userInfo.getStatus()>1){
            for (int i =0;i<200;i++){
                Thread thread=new Thread(new CountThread(userInfo),"线程"+i);
                threadPoolTaskExecutor.execute(thread);
            }
        }



    }



    @ApiOperation("测试Redis事务")
    @RequestMapping(value = "/test10", method = RequestMethod.POST)
    @ResponseBody
    public void test10(){
        redisTemplate.setEnableTransactionSupport(true);        //开启事务支持
        redisTemplate.delete("t");
        redisTemplate.multi();
        redisTemplate.opsForList().leftPush("t","1");
        redisTemplate.opsForList().leftPush("t","2");
        int a = 6 / 0;
        System.out.println(a);
        redisTemplate.opsForList().leftPush("t","3");
        redisTemplate.opsForList().leftPush("t","4");
        redisTemplate.opsForList().leftPush("t","5");
        redisTemplate.exec();
    }

    @ApiOperation("测试Redis事务")
    @RequestMapping(value = "/test11", method = RequestMethod.POST)
    @ResponseBody
    public void test11(){
        System.out.println(redisTemplate.opsForList().leftPop("t"));
        System.out.println(redisTemplate.opsForList().leftPop("t"));
        System.out.println(redisTemplate.opsForList().leftPop("t"));
        System.out.println(redisTemplate.opsForList().leftPop("t"));
        System.out.println(redisTemplate.opsForList().leftPop("t"));
    }

    @ApiOperation("测试Redis watch秒杀")
    @RequestMapping(value = "/test12", method = RequestMethod.POST)
    @ResponseBody
    public void test12(){
        int count=100;
        integerRedisTemplate.opsForValue().set("watchKey",100);
        System.out.println("设置秒杀商品数量："+count);

        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new SecKillThread(integerRedisTemplate));
            threadPoolTaskExecutor.execute(thread);
        }
    }


    @ApiOperation("测试并发接口")
    @RequestMapping(value = "/test13", method = RequestMethod.POST)
    @ResponseBody
    public void test13(){
        for (int i = 0; i < 50; i++) {
            threadPoolTaskExecutor.execute(new Thread(()->{
                MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
                requestEntity.add("json", "{\"wAction\":\"5002\",\"wAgent\":\"2\",\"wParam\":{\"token\":\"f4d2cfcc-d417-4fd8-a843-c7866a31ce57\",\"wLotteryId\":\"0\",\"ticket\":{\"userId\":\"436\",\"appParamsId\":\"1072\"}},\"wSign\":\"0C56875CF01B38A839184881096D5773\"}");
                String s = restTemplate.postForObject("http://192.168.1.15:8080/appinterface.jsp", requestEntity, String.class);
                System.out.println(s);
            }));
        }
    }

    

    public static void main(String[] args) throws ParseException {
/*    Byte i = new Byte("127");
    System.out.println(i);
    
    int j = 2147483647;
    System.out.println(4294967296l/2);
    
    
    float f1 =0.99999999f;  //7位
    float f2 =1f;
    System.out.println(f1==f2);*/
        
//        HashSet set =new HashSet<Integer>();
//        
//        LinkedHashMap<Integer, Object> linkmap = new LinkedHashMap<Integer,Object>();
//        Object[] a = {1,'a',3,4,"啊","&",7,8,2};
//        for (int i = 0; i < a.length; i++) {
//            set.add(a[i]);
//            linkmap.put(i, a[i]);
//        }
//        System.out.println(set.toString());
//        
//        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
//            Object object = (Object) iterator.next();
//            System.out.println(object.toString());
//        }
//        
//        for(Integer i : linkmap.keySet()){
//            System.out.println(linkmap.get(i));
//        }
//        
//        List<Integer> list = new ArrayList<Integer>();  
//        for (int i = 0; i < 10; i++)  {
//            list.add(new Integer(i));  
//        }
//        
//        Collections.shuffle(list);
//        
//        System.out.println(list);
       
        UserInfo us =new UserInfo();
        us.setNickName("111");
        updateUser(us);
        System.out.println(us.getNickName());
    }
    
    public static void updateUser(UserInfo u){
        u.setNickName("222");
    }
    
    
    public static String a(){
        String  s = null;
        try {
              s="111";
            return s;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return s;
        }finally {
            s="2222";
            return s;
        }
        
    }
    
    
   



}
