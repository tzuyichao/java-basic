package net;

import java.io.IOException;
import java.net.Socket;

public class BacklogDefaultLabClient {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {
                Socket socket = new Socket("localhost", 8088);
                System.err.println("client connect:" + (i + 1) + ": " + System.currentTimeMillis());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
