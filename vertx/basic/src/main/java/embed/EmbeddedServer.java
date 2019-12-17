package embed;

import io.vertx.core.Vertx;

public final class EmbeddedServer {
    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("hello, world!")).listen(8080);
    }
}