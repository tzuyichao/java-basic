package io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

public class SimplePipedMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedOutputStream clientPipedOutput = new PipedOutputStream();
        PipedOutputStream serverPipedOutput = new PipedOutputStream();
        PipedInputStream serverPipedInput = new PipedInputStream(clientPipedOutput);
        PipedInputStream clientPipedInput = new PipedInputStream(serverPipedOutput);

        new Thread(new SimplePipedServer(serverPipedInput, serverPipedOutput)).start();
        new Thread(new SimplePipedClient(clientPipedInput, clientPipedOutput)).start();

        TimeUnit.SECONDS.sleep(10);
    }
}
