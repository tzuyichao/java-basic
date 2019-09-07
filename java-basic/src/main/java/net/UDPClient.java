package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.connect(new InetSocketAddress("localhost", 8888));
        String newString = "1234567890";
        byte[] bytes = newString.getBytes();
        DatagramPacket myPacket = new DatagramPacket(bytes, bytes.length);
        socket.send(myPacket);
        socket.close();
    }
}
