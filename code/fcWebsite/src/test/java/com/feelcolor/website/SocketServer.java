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
            if (socket != null) {
                InputStream is = null;
                OutputStream os = null;
                String ip = socket.getInetAddress().toString();
                System.out.println(ip);

                is = socket.getInputStream();
                os = socket.getOutputStream();
                byte[] tmp = new byte[2000];
                is.read(tmp);

                os.write(("服务器接收到发送消息：" + new String(tmp)).getBytes());

                is.close();
                os.close();
            }
        }
    }

}
