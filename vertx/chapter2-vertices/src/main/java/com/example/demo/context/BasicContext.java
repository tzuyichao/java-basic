package com.example.demo.context;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicContext {
  private static final Logger logger = LoggerFactory.getLogger(BasicContext.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.getOrCreateContext()
      .runOnContext(event -> {
        logger.info("ABC {}", event);
      });
    vertx.getOrCreateContext()
      .runOnContext(event -> {
        logger.info("123 {}", event);
      });

    vertx.getOrCreateContext()
      .put("foo", "bar");

    vertx.exceptionHandler(t -> {
      if("Tada".equals(t.getMessage())) {
        logger.info("Got a _Tada_ exception");
      } else {
        logger.error("Woops", t);
      }
    });

    Context ctx = vertx.getOrCreateContext();
    vertx.getOrCreateContext()
      .runOnContext(event -> {
        throw new RuntimeException("Tada");
      });

    vertx.getOrCreateContext()
      .runOnContext(event -> {
        logger.info("foo = {}", (String) ctx.get("foo"));
      });

    vertx.close(asyncResult -> {
      if(asyncResult.succeeded()) {
        logger.info("close vertx completed");
      } else {
        logger.error("close vertx failed", asyncResult.cause());
      }
    });
  }
}
