package com.feelcolor.website.thread;

import java.util.concurrent.CountDownLatch;

public class test {


    
    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch =new CountDownLatch(2);
        AllOverCountDownLatch t1 =new AllOverCountDownLatch("线程一",5000,latch);
        AllOverCountDownLatch t2 =new AllOverCountDownLatch("线程二",10000,latch);
        t1.start();
        t2.start();
        latch.await();//等待所有线程执行完毕
        System.out.println("所有线程执行完毕");
    }
}
