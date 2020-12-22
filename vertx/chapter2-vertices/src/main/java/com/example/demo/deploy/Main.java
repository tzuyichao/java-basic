package com.example.demo.deploy;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Deployer(), ar -> {
      if(ar.succeeded()) {
        logger.info("deploy Deployer completed");
      } else {
        logger.error("deploy Deployer fail", ar.cause());
      }
    });
  }
}
