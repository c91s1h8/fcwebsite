package com.feelcolor.website.thread;

import java.util.Queue;
import java.util.Random;

public class ConsumerThread extends  Thread {

    private Queue queue;

    private Integer count =0;

    public ConsumerThread(Queue queue,String name) {
        super(name);
        this.queue = queue;
    }


    @Override
    public void run() {
        while (count<=30){
            synchronized (queue){
                while(queue.isEmpty()){
                    System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming value : " + queue.remove());
                count++;
                queue.notifyAll();
            }
        }
        System.out.println("已经消费"+count+"次，"+Thread.currentThread().getName()+"线程退出");
    }
}
