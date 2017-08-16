package com.feelcolor.website.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
//@Scope("prototype")
public class TCPClient {
    @Value("${app.socket.host}")
    private String host;
    @Value("${app.socket.port}")
    private Integer port;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private PrintWriter pw;
    static private Socket clientSocket;

    public TCPClient() {}

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String serverIP;

        System.out.println("请设置服务器IP：");
        serverIP = scanner.next();
        clientSocket = new Socket(serverIP, 6788);
        TCPClient client = new TCPClient();
        client.start("");
    }

    public void sendMsg(String msg){
        pw.println(msg);
    }

    public void start(String name) {
        try {
            clientSocket = new Socket(host, port);
            setName(name);

            // 接收服务器端发送过来的信息的线程启动
            threadPoolTaskExecutor.execute(new ListenrServser());

            // 建立输出流，给服务端发信息
            pw = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"), true);

            pw.println(name);
            while(true) {

            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSocket !=null) {
                try {
                    clientSocket.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setName(String name) throws Exception {
        //创建输出流
        PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"),true);
        //创建输入流
        BufferedReader br = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));

        while(true) {
            if (name.trim().equals("")) {
                System.out.println("昵称不得为空");
            } else {
                pw.println(name);
                String pass = br.readLine();
                if (pass != null && (!pass.equals("OK"))) {
                    System.out.println("昵称已经被占用，请重新输入：");
                } else {
                    System.out.println("昵称“"+name+"”已设置成功，可以开始聊天了");
                    break;
                }
            }
        }
    }

    // 循环读取服务端发送过来的信息并输出到客户端的控制台
    class ListenrServser implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                String msgString;
                while((msgString = br.readLine())!= null) {
                    System.out.println(msgString);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}