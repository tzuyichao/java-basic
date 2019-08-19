package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadPoolLabServer {
    static class ReadHandler implements Runnable {
        private Socket socket;

        ReadHandler(Socket socket) {
            this.socket = socket;
        }

        String getRemoteInfo() {
            StringBuilder stringBuilder = new StringBuilder();
            SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
            stringBuilder.append("Remote: ")
                    .append(remoteSocketAddress)
                    .append(": ");
            return  stringBuilder.toString();
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                char[] chars = new char[1000];
                int readLength = inputStreamReader.read(chars);
                while (readLength != -1) {
                    System.err.println(getRemoteInfo() + new String(chars, 0, readLength));
                    readLength = inputStreamReader.read(chars);
                }
                inputStreamReader.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Server {
        private ServerSocket serverSocket;
        private Executor executor;

        Server(int port, int poolSize) {
            try {
                serverSocket = new ServerSocket(port);
                executor = Executors.newFixedThreadPool(poolSize);
                System.err.println("Server on " + port + " with " + poolSize + " size thread pool.");
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        public void startService() {
            try {
                for(;;) {
                    Socket socket = serverSocket.accept();
                    executor.execute(new ReadHandler(socket));
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8088, 100);
        server.startService();
    }
}
