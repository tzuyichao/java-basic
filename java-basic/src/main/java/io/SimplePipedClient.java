package io;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class SimplePipedClient extends Thread {
    private PipedInputStream pipedIn;
    private PipedOutputStream pipedOut;

    public SimplePipedClient(PipedInputStream pipedIn, PipedOutputStream pipedOut) {
        this.pipedIn = pipedIn;
        this.pipedOut = pipedOut;
    }

    @Override
    public void run() {
        DataInputStream input = new DataInputStream(pipedIn);
        DataOutputStream output = new DataOutputStream(pipedOut);
        try {
            System.out.println("SimplePipedClient: Writing message to the server: ");
            output.writeChars("Message from the SimplePipedClient to the Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("SimplePipedClient: Reading response from the server: ");
            String response = input.readUTF();
            System.out.println("SimplePipedClient: Server response : " + response);
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
