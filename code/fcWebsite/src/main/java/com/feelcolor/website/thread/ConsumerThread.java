package com.feelcolor.website.thread;

import java.util.Queue;
import java.util.Random;

public class ConsumerThread extends  Thread {

    private Queue queue;

    public ConsumerThread(Queue queue,String name) {
        super(name);
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true){
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
                queue.notifyAll();
            }
        }
    }
}
