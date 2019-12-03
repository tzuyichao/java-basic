package discard0;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class DiscardServer {
    static final boolean SSL = System.getProperty("ssl") != null;

    public static void main(String[] args) {
        log.info("SSL: {}", SSL);
    }
}
