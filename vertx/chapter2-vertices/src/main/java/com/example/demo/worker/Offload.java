package com.example.demo.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * using executeBlocking
 */
public class Offload extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(Offload.class);

  @Override
  public void start() throws Exception {
    vertx.setPeriodic(5000, id -> {
      logger.info("Tick {}", id);
      vertx.executeBlocking(this::blockingCode, this::resultHandler);
    });
  }

  private void blockingCode(Promise<String> promise) {
    try {
      Thread.sleep(4000);
      logger.info("Done!");
      promise.complete("Ok!");
    } catch (InterruptedException e) {
      promise.fail(e);
    }
  }

  private void resultHandler(AsyncResult<String> asyncResult) {
    if(asyncResult.succeeded()) {
      logger.info("Blocking code result: {}", asyncResult.result());
    } else {
      logger.error("Woops", asyncResult.cause());
    }
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("com.example.demo.worker.Offload", ar -> {
      if(ar.succeeded()) {
        logger.info("deploy completed {}", ar.result());
      } else {
        logger.error("deploy failed", ar.cause());
      }
    });
  }
}
