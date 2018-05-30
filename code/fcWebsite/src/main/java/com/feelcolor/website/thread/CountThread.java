package com.feelcolor.website.thread;

import com.feelcolor.website.model.po.UserInfo;

public class CountThread extends Thread {
    private UserInfo userInfo;

    public CountThread(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
      //  synchronized (userInfo){
            if(userInfo.getStatus()>=0){
                try {
                    userInfo.setStatus(userInfo.getStatus()-1);
                  sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"线程获取到："+userInfo.getStatus());
            }
      //  }
    }
}
