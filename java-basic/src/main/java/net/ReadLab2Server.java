package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadLab2Server {
    public static void main(String[] args) {
        try {
            char[] chars = new char[3];
            ServerSocket serverSocket = new ServerSocket(8088);
            System.err.println("accept begin: " + System.currentTimeMillis());
            Socket socket = serverSocket.accept();
            System.err.println("accept end: " + System.currentTimeMillis());

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            System.err.println("read begin: " + System.currentTimeMillis());
            int readLength = inputStreamReader.read(chars);
            while(readLength != -1) {
                String receiveData = new String(chars, 0, readLength);
                System.err.println("Receive Data: " + receiveData);
                readLength = inputStreamReader.read(chars);
            }
            System.err.println("read end: " + System.currentTimeMillis());
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
