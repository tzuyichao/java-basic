package com.example.demo.deploy;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyVerticle extends AbstractVerticle {
  private static Logger logger = LoggerFactory.getLogger(EmptyVerticle.class);
  @Override
  public void start() throws Exception {
    logger.info("start");
  }

  @Override
  public void stop() throws Exception {
    logger.info("stop");
  }
}
