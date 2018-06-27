package com.feelcolor.website.thread;

public class JoinThread extends Thread {

    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程执行开始");
        try {
            Thread.sleep(5000l);
            System.out.println(Thread.currentThread().getName()+"线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
