package com.example.demo.heat;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeatSensor extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    log.info("HeatSensor start");
  }
}
