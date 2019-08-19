package net;

import java.io.*;
import java.net.Socket;

public class UserInfoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8088);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            System.err.println("Starting...");
            for(int i=0; i<5; i++) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(i);
                userInfo.setUsername("C" + (i+1));
                userInfo.setPassword("C" + (i+1));

                objectOutputStream.writeObject(userInfo);
                System.err.println("Writing ..." + userInfo.toString());

                UserInfo incomingUserInfo = (UserInfo) objectInputStream.readObject();
                System.err.println("Client get " + (i+1) + ": " + incomingUserInfo.toString());
            }
            objectInputStream.close();
            objectOutputStream.close();

            inputStream.close();
            outputStream.close();

            socket.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
