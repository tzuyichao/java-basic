package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class WriteLab1Client {
    public static void main(String[] args) {
        try {
            System.err.println("client prepare connect: " + System.currentTimeMillis());
            Socket socket = new Socket("localhost", 8088);
            System.err.println("Client connected: " + System.currentTimeMillis());

            char[] chars = new char[3];
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            System.err.println("Begin reading: " + System.currentTimeMillis());
            int readLength = inputStreamReader.read(chars);
            while(readLength != -1) {
                System.err.println(new String(chars, 0, readLength));
                readLength = inputStreamReader.read(chars);
            }
            System.err.println("End reading: " + System.currentTimeMillis());;
            inputStreamReader.close();
            inputStream.close();
            socket.close();
            System.err.println("Client completed.");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
