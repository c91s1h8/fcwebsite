package com.feelcolor.website;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.2.183", 10088);
        if (socket.isConnected()) {
            InputStream is;
            OutputStream os;
            while (true){
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String in  =br.readLine();
                os = socket.getOutputStream();
                os.write(in.getBytes());
                os.flush();

                is = socket.getInputStream();
                byte[] tmp = new byte[2000];
                is.read(tmp);
                System.out.println("客户端收到返回消息:"+new String(tmp));

//                is.close();
//                os.close();
            }
        }
    }
}
