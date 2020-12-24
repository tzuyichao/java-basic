package com.example.demo.heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    int port = config().getInteger("port", 8080);
    vertx.createHttpServer()
      .requestHandler(this::handler)
      .listen(port, ar -> {
        if(ar.succeeded()) {
          log.info("HTTP server listen on {}", port);
        } else {
          log.error("HTTP server start failed", ar.cause());
        }
      });
  }

  private void handler(HttpServerRequest request) {
    if("/".equals(request.path())) {
      request.response().sendFile("index.html");
    }
  }
}
