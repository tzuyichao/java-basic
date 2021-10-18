package io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class SimplePipeStream {
    public static void main(String[] args) throws IOException {
        final PipedOutputStream pipedOut = new PipedOutputStream();
        final PipedInputStream pipedIn = new PipedInputStream(pipedOut);

        Thread writeOut = new Thread(() -> {
            try {
                pipedOut.write("Hello world, pipe!".getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread readIn = new Thread(() -> {
            try {
                int pipedData = pipedIn.read();
                while (pipedData != -1) {
                    System.out.println((char) pipedData);
                    pipedData = pipedIn.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writeOut.start();
        readIn.start();
    }
}
