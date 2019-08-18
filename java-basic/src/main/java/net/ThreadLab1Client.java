package net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ThreadLab1Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8088);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("這是client送出的測試訊息".getBytes());
            outputStream.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
