package embed;

import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public final class EmbeddedServer {
    private static final Logger log = LoggerFactory.getLogger(EmbeddedServer.class);

    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("hello, world!")).listen(8080);
        log.info("Server Started.");
    }
}