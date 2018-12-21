package com.feelcolor.website.learn.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Administrator on 2018/12/14 0014.
 */
public class HeavyThreadEchoClient {
    static Long sleep_time=1000*1000*1000L;
    static ExecutorService es  =  Executors.newCachedThreadPool();
    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        for (int i = 0; i < 10; i++) {
            es.execute(echoClient);
        }
    }

    public static class EchoClient implements Runnable{

        @Override
        public void run() {
            Socket client =null;
            BufferedReader reader =null;
            PrintWriter writer =null;
            try {
                 client = new Socket();
                client.connect(new InetSocketAddress("localhost",8006));
                 writer =  new PrintWriter(client.getOutputStream());
                writer.print("h");
                LockSupport.parkNanos(sleep_time);
                writer.print("e");
                LockSupport.parkNanos(sleep_time);

                writer.print("l");
                LockSupport.parkNanos(sleep_time);

                writer.print("l");
                LockSupport.parkNanos(sleep_time);

                writer.print("o");
                LockSupport.parkNanos(sleep_time);

                writer.print("!");
                LockSupport.parkNanos(sleep_time);

                writer.println();
                writer.flush();
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server:"+reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer!=null){
                    writer.close();
                }
                if(reader!=null){
                    try {
                        reader.close();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }

                }
                if(client!=null){
                    try {
                        client.close();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            }

        }
    }
}
