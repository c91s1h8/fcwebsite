package com.feelcolor.website.thread;

import com.feelcolor.website.model.po.UserInfo;

import java.util.PriorityQueue;

public class WaitThread implements Runnable {


    private UserInfo userInfo;

    public WaitThread(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        try {
            synchronized (userInfo) {
                while (userInfo.getStatus() == 0) {
                    System.out.println("小于100 Wati等待执行");
                    userInfo.wait();
                }
                userInfo.setSex(userInfo.getSex() - 1);
                System.out.println("WatiThread " + userInfo.getSex());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
