package com.feelcolor.website.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionQueue {
    int max= 5;

    LinkedList<Integer> queue = new LinkedList<Integer>();


    Lock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();


    public void produce(Integer value){
        lock.lock();
        try {
            while(max==queue.size()){
                System.out.println("队列已满，停止生产");
                full.await();
            }
            System.out.println("生产==="+value);
            queue.add(value);
            empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consume(){
        lock.lock();
        try {
            while(0==queue.size()){
                System.out.println("队列已空，等待生产");
                empty.await();
            }
            System.out.println("消费==="+queue.getFirst());
            queue.removeFirst();
            full.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



}
