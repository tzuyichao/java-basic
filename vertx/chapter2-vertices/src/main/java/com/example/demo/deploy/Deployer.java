package com.example.demo.deploy;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deployer extends AbstractVerticle {
  private static final Logger logger = LoggerFactory.getLogger(Deployer.class);

  @Override
  public void start() throws Exception {
    long delay = 1000;
    for(int i=0; i<50; i++) {
      vertx.setTimer(delay, id -> deploy());
      delay = delay + 1000;
    }
  }

  private void deploy() {
    vertx.deployVerticle(new EmptyVerticle(), ar -> {
      if(ar.succeeded()) {
        String id = ar.result();
        logger.info("Successfully deployed {}", id);
        vertx.setTimer(5000, tid  -> undeploy(id));
      } else {
        logger.error("Error with deploying", ar.cause());
      }
    });
  }

  private void undeploy(String deploymentId) {
    vertx.undeploy(deploymentId, ar -> {
      if(ar.succeeded()) {
        logger.info("{} was undeployed", deploymentId);
      } else {
        logger.error("{} could not be undeployed", deploymentId);
      }
    });
  }
}
