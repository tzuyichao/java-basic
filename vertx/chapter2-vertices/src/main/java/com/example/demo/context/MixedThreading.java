package com.example.demo.context;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class MixedThreading extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(MixedThreading.class);

  @Override
  public void start() throws Exception {
    Context context = vertx.getOrCreateContext();
    new Thread(() -> {
      try {
        run(context);
      } catch (InterruptedException e) {
        logger.error("Woops", e);
      }
    }).start();
  }

  private void run(Context context) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(1);
    logger.info("I am in a non-Vert.x thread");
    context.runOnContext(event -> {
      logger.info("I am on the event-loop");
      vertx.setTimer(1000, id -> {
        logger.info("This is the final countdown");
        countDownLatch.countDown();
      });
    });
    logger.info("waiting on the countdown latch");
    countDownLatch.await();
    logger.info("Bye");
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("com.example.demo.context.MixedThreading");
  }
}
