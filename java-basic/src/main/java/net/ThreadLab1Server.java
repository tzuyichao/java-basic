package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadLab1Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            boolean running = true;
            while(running) {
                Socket socket = serverSocket.accept();
                HandlerThread worker = new HandlerThread(socket);
                worker.start();
            }
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static class HandlerThread extends Thread {
        private Socket socket;

        HandlerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                char[] chars = new char[1000];
                int readLength = -1;
                while((readLength = inputStreamReader.read(chars)) != -1) {
                    String message = new String(chars, 0, readLength);
                    System.err.println(message);
                }
                inputStreamReader.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
