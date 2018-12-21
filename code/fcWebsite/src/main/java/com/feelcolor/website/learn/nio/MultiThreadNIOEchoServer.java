package com.feelcolor.website.learn.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/12/14 0014.
 */
public class MultiThreadNIOEchoServer {
    private Selector selector;
    public static Map<Socket, Long> geym_time_stat = new HashMap<Socket, Long>(10240);

    private ExecutorService tp = Executors.newCachedThreadPool();

    class EchoClient {
        private LinkedList<ByteBuffer> outq;

        EchoClient() {
            outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue() {
            return outq;
        }

        public void enqueue(ByteBuffer bb) {
            outq.addFirst(bb);
        }
    }


    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }


    private void startServer() throws IOException {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8006);
        ssc.socket().bind(inetSocketAddress);

        ssc.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();

                if (sk.isAcceptable()) {
                    doAccept(sk);
                } else if (sk.isValid() && sk.isReadable()) {
                    if (!geym_time_stat.containsKey(((SocketChannel) sk.channel()).socket())) {
                        geym_time_stat.put(((SocketChannel) sk.channel()).socket(), System.currentTimeMillis());
                        doRead(sk);
                    }
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e=System.currentTimeMillis();
                    long b=geym_time_stat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend"+(e-b)+"ms");

                }
            }

        }
    }

    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        EchoClient  echoClient = (EchoClient) sk.attachment();

        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
        ByteBuffer bb =outq.getLast();
        try {
            int len  =channel.write(bb);
            if(len==-1){
                disconnect(sk);
                return;
            }
            if(bb.remaining()==0){
                outq.removeLast();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail to write to client");
            disconnect(sk);
        }
        if(outq.size()==0){
            sk.interestOps(SelectionKey.OP_READ);
        }

    }

    private void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;
        try {
            len = channel.read(bb);
            if (len < 0) {
                disconnect(sk);
                return;
            }
            bb.flip();
            tp.execute(new HandleMsg(sk, bb));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel clientChannel = null;
        try {
            clientChannel = server.accept();
            clientChannel.configureBlocking(false);
            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);
            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connetion from " + clientAddress.getHostAddress() + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void disconnect(SelectionKey sk) {
        SocketChannel sc = (SocketChannel) sk.channel();
        try {
            sc.finishConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        MultiThreadNIOEchoServer echoServer = new MultiThreadNIOEchoServer();
        try {
            echoServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
