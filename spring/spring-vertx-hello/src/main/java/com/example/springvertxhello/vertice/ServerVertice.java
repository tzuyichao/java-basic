package com.example.springvertxhello.vertice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Log
@Component
public class ServerVertice extends AbstractVerticle {
    public static final String DEFAULT_PORT = "8000";

    @Autowired
    Environment environment;

    @Override
    public void start() throws Exception {
        super.start();
        Router router = Router.router(vertx);
        log.info(environment.getProperty("server.port") == null?"server.port not set":"server.port found");
        final int port = Integer.parseInt(environment.getProperty("server.port", DEFAULT_PORT));

        router.get("/hello")
                .handler(routingContext -> {
                    HttpServerResponse response = routingContext.response();
                    response.putHeader("Content-Type", "text/plain");
                    response.end("Hello, World!");
                });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(port);
        log.info("server running at " + port);
    }
}
