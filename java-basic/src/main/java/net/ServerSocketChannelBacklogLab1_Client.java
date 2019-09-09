package net;

import java.io.IOException;
import java.net.Socket;

public class ServerSocketChannelBacklogLab1_Client {
    public static void main(String[] args) throws IOException {
        for(int i=0; i<1000; i++) {
            Socket socket = new Socket("localhost", 8888);
            socket.close();
            System.out.println("用戶端連接數為 " + (i+1));
        }
    }
}
