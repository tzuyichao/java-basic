package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BacklogDefaultLabServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            Thread.sleep(8000);

            for(int i=0; i<100; i++) {
                System.err.println("accept begin " + (i+1));
                Socket socket = serverSocket.accept();
                System.err.println("accept end " + (i+1));
            }
            serverSocket.close();
        } catch(IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
