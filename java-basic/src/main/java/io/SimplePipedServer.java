package io;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class SimplePipedServer extends Thread {
    private PipedInputStream pipedIn;
    private PipedOutputStream pipedOut;

    public SimplePipedServer(PipedInputStream pipedIn, PipedOutputStream pipedOut) {
        this.pipedIn = pipedIn;
        this.pipedOut = pipedOut;
    }

    @Override
    public void run() {
        DataInputStream input = new DataInputStream(pipedIn);
        DataOutputStream output = new DataOutputStream(pipedOut);
        try {
            System.out.println("SimplePipedServer: Reading message from client: ");
            String clientMessage = input.readUTF();
            System.out.println("SimplePipedServer: Client message: " + clientMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("SimplePipedServer: Writing message to the client: ");
            output.writeChars("Message from the server.");
            System.out.println("SimplePipedServer: Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
