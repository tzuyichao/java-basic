package net;

import java.io.IOException;
import java.net.ServerSocket;

public class AcceptLabServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8088);
            System.err.println("Server Blocking Start=" + System.currentTimeMillis());
            socket.accept();
            System.err.println("Server Blocking End=" + System.currentTimeMillis());
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
