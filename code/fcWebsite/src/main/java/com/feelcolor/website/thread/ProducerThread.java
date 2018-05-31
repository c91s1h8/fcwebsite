package com.feelcolor.website.thread;

import java.util.Queue;
import java.util.Random;

public class ProducerThread extends Thread {

    private Queue queue;

    private Integer maxSize=10;

    public ProducerThread(Queue queue,String name) {
        super(name);
        this.queue = queue;
    }


    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while(queue.size()==maxSize){
                    System.out .println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Producing value : " + i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}
