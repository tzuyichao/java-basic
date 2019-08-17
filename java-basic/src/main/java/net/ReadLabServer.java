package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadLabServer {
    public static void main(String[] args) {
        try {
            byte[] bytes = new byte[1024];
            ServerSocket serverSocket = new ServerSocket(8088);
            System.err.println("accept begin: " + System.currentTimeMillis());
            Socket socket = serverSocket.accept();
            System.err.println("accept end: " + System.currentTimeMillis());

            InputStream inputStream = socket.getInputStream();
            System.err.println("Read begin: " + System.currentTimeMillis());
            int readLength = inputStream.read(bytes);
            System.err.println("Read end: " + System.currentTimeMillis());
            socket.close();
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
