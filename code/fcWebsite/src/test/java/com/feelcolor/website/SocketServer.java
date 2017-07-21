package com.feelcolor.website;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    private static final int port = 10088;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("等待客户端链接");
            Socket socket = serverSocket.accept();
            new SocketThread(socket).start();
        }
    }

}
