package com.feelcolor.website.thread;

import com.feelcolor.website.model.po.UserInfo;

public class NotifyThread extends Thread{

    private UserInfo userInfo;

    public NotifyThread(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        synchronized (userInfo) {
            while(userInfo.getStatus()==0){
                userInfo.setSex(userInfo.getSex() + 1);
                System.out.println("NotifyThread " + userInfo.getSex());
                if (userInfo.getSex() == 100) {
                    System.out.println("NotifyThread 唤醒WaitThread");
                    userInfo.notify();
                    userInfo.setStatus(1);
                }
            }
        }
    }
}
