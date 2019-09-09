package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketChannelBacklogLab1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888), 30);
        ServerSocket serverSocket = serverSocketChannel.socket();
        Thread.sleep(5000);
        boolean isRun = true;
        while(isRun == true) {
            Socket socket = serverSocket.accept();
            socket.close();
        }
        Thread.sleep(8000);
        serverSocket.close();
        serverSocketChannel.close();
    }
}
