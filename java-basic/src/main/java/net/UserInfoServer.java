package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class UserInfoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            Socket socket = serverSocket.accept();
            // Server 和 Client取得ObjectInputStream和ObjectOutputStream順序很重要
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            for(int i=0; i<5; i++) {
                UserInfo userInfo = (UserInfo)objectInputStream.readObject();
                System.err.println("Server dump " + (i+1) + ":" + userInfo.toString());

                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setId(i+1);
                newUserInfo.setUsername("S" + (i+1));
                newUserInfo.setPassword("S" + (i+1));

                objectOutputStream.writeObject(newUserInfo);
            }
            objectInputStream.close();
            objectOutputStream.close();
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
