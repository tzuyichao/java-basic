package org.greenrivers.hello.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServerVerticle extends AbstractVerticle {
    private Integer defaultPort = 8088;

    private void hello(RoutingContext routingContext) {
        routingContext.response()
                .setStatusCode(200)
                .end("Hello, World!");
    }

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        router.get("/hello")
                .handler(this::hello);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", defaultPort));
        log.info("Running...");
    }
}
