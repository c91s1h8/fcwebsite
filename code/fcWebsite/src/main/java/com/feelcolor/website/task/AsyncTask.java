package com.feelcolor.website.task;

import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AsyncTask {
    @Resource
    private RestTemplate restTemplate;

    @Async
    public Future<String> returnAsync() throws InterruptedException {
        System.out.println("开始执行异步请求============================");
        Thread.sleep(10000);
        System.out.println("============================异步请求执行完毕");
        return new AsyncResult<String>("我是结果");
    }

    @Async
    public void voidAsync() throws InterruptedException {
        System.out.println("开始执行异步请求============================");
        Thread.sleep(10000);
        System.out.println("============================异步请求执行完毕");
    }
}
