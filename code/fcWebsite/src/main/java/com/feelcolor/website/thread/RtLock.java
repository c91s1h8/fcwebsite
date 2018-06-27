package com.feelcolor.website.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 */
public class RtLock {
    Lock reenTrantLock = new ReentrantLock();


    public static void main(String[] args) {
        final RtLock rtLock = new RtLock();
        new Thread(){
            @Override
            public void run() {
                rtLock.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                rtLock.insert(Thread.currentThread());
            }
        }.start();

    }


    public void insert(Thread thread){
        reenTrantLock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reenTrantLock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }

    }

}
