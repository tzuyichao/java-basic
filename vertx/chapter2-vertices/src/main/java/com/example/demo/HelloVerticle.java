package com.example.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(HelloVerticle.class);
  private long counter = 1;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.setPeriodic(5000, id -> {
      logger.info("tick");
    });

    vertx.createHttpServer()
      .requestHandler(req -> {
        logger.info("Request #{} from {}", counter++, req.remoteAddress().host());
        req.response().end("Hello there!");
      })
      .listen(8888, http -> {
        if(http.succeeded()) {
          startPromise.complete();
          logger.info("open http://localhost:8888");
        } else {
          startPromise.fail(http.cause());
        }
      });
  }
}
