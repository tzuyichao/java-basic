package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WriteLab1Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            System.err.println("Server blocking start: " + System.currentTimeMillis());
            Socket socket = serverSocket.accept();
            System.err.println("Server blocking end: " + System.currentTimeMillis());

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("來自Server端的訊息".getBytes());
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
