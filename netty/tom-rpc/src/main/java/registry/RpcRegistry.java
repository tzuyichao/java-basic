package registry;

import lombok.extern.java.Log;

@Log
public class RpcRegistry {
    public static final int DEFAULT_PORT = 8000;
    private int port;

    public RpcRegistry() {
        this(DEFAULT_PORT);
    }

    public RpcRegistry(int port) {
        this.port = port;
    }

    public void start() {
        log.info("Server startup...");

        log.info("Server running on " + port);
    }

    public static void main(String[] args) {
        new RpcRegistry().start();
    }
}
