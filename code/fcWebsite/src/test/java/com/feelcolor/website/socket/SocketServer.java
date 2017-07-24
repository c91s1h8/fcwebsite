package com.feelcolor.website.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketServer {
    private static final int port = 10088;
    

    public static void main(String[] args) throws IOException {
        Map<InetAddress,Socket> sockets = new HashMap<InetAddress,Socket>();
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("等待客户端链接");
            InetAddress ip = serverSocket.getInetAddress();
            Socket socket = serverSocket.accept();
            if(sockets.get(ip)==null){
                sockets.put(ip, socket);
                new SocketThread(socket).start();
            }else{
                new SocketThread(sockets.get(ip)).start();
            }
           
        }
    }

}
