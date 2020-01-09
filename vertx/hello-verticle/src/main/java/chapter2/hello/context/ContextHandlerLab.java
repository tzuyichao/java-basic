package chapter2.hello.context;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextHandlerLab {
    private static final String KEY_FOO = "foo";
    private static final String TADA_EXCEPTION = "TADA";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        log.info("Running...");
        Context ctx = vertx.getOrCreateContext();
        ctx.put(KEY_FOO, "bar");
        ctx.exceptionHandler(cause -> {
            if(TADA_EXCEPTION.equals(cause.getMessage())) {
                log.error("Got a _TADA_ exception");
            } else {
                log.error("Woops!", cause);
            }
        });

        ctx.runOnContext(event -> {
            throw new RuntimeException(TADA_EXCEPTION);
        });

        ctx.runOnContext(event -> {
            log.info("foo = {}", (String) ctx.get(KEY_FOO));
        });

        ctx.runOnContext(event -> {
            throw new IllegalStateException("testing");
        });
    }
}
