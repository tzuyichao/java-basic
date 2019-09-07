package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] bytes = new byte[12];
        DatagramPacket myPacket = new DatagramPacket(bytes, 10);
        socket.receive(myPacket);
        socket.close();
        System.out.println("Packet length: " + myPacket.getLength());
        System.out.println(new String(myPacket.getData(), 0, myPacket.getLength()));
    }
}
