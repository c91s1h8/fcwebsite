package com.feelcolor.website.thread;

import java.util.concurrent.CountDownLatch;

public class AllOverCountDownLatch extends Thread{

    private String name;
    private int time;
    private CountDownLatch latch;

    public AllOverCountDownLatch(String name,int time,CountDownLatch latch) {
        super();
        this.name = name;
        this.time=time;
        this.latch=latch;
    }

    @Override
    public void run() {

        while (true){
            synchronized (latch){
                if(latch.getCount()==2){
                    System.out.println("线程："+name+"，================================执行完毕");
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    latch.countDown();
                }else if(latch.getCount()==1){
                    System.out.println("线程："+name+"，开始执行================================");
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    latch.countDown();
                    System.out.println("线程："+name+"，================================执行完毕");
                }else{
                    System.out.println("线程："+name+"，等待中================================");
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch =new CountDownLatch(2);
        AllOverCountDownLatch t1 =new AllOverCountDownLatch("线程一",5000,latch);
        AllOverCountDownLatch t2 =new AllOverCountDownLatch("线程二",5000,latch);
        t1.start();
        t2.start();
        latch.await();//等待所有线程执行完毕
        System.out.println("所有线程执行完毕");
    }
}
