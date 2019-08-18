package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ReadLab2Client {
    public static void main(String[] args) {
        try {
            System.err.println("socket begin: " + System.currentTimeMillis());
            Socket socket = new Socket("localhost", 8088);
            System.err.println("socket end: " + System.currentTimeMillis());
            Thread.sleep(3000);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("這是測試".getBytes());
            Thread.sleep(3000);
            outputStream.flush();
            System.err.println("write complete: " + System.currentTimeMillis());
            outputStream.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
