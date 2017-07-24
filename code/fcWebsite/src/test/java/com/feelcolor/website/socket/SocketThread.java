package com.feelcolor.website.socket;

import java.io.*;
import java.net.Socket;

public class SocketThread extends Thread {
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public SocketThread(Socket socket) {
        System.out.println("init====================================");
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("run====================================");
        while (true){
            try {
                String str  = br.readLine();
                if("exit".equals(str)){
                    br.close();
                    pw.close();
                    socket.close();
                    break;
                }
                pw.write("服务器收到消息："+str);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    br.close();
                    pw.close();
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
