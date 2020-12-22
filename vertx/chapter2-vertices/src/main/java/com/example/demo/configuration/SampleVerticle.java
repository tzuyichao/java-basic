package com.example.demo.configuration;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleVerticle extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(SampleVerticle.class);
  public static final String KEY_N = "n";

  @Override
  public void start() throws Exception {
    logger.info("n = {}", config().getInteger(KEY_N, -1));
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    for(int i=0; i<4; i++) {
      JsonObject conf = new JsonObject().put(KEY_N, i);
      DeploymentOptions deploymentOptions = new DeploymentOptions()
        .setConfig(conf)
        .setInstances(i);
      vertx.deployVerticle("com.example.demo.configuration.SampleVerticle",
        deploymentOptions,
        ar -> {
        if(ar.succeeded()) {
          logger.info("Deploy {}", ar.result());
        } else {
          logger.error("Deploy fail", ar.cause());
        }
      });
    }
  }
}
