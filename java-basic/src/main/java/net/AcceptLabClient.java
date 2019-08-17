package net;

import java.io.IOException;
import java.net.Socket;

public class AcceptLabClient {
    public static void main(String[] args) {
        try {
            System.err.println("Client prepare connect=" + System.currentTimeMillis());
            Socket socket = new Socket("localhost", 8088);
            System.err.println("Client connect end=" + System.currentTimeMillis());
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
