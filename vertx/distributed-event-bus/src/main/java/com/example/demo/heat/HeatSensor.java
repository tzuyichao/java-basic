package com.example.demo.heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

@Slf4j
public class HeatSensor extends AbstractVerticle {
  private final Random random = new Random();
  private final String sensorId = UUID.randomUUID().toString();
  private double temperature = 21.0;

  @Override
  public void start() throws Exception {
    log.info("HeatSensor start");
    scheduleNextUpdate();
  }

  private void scheduleNextUpdate() {
    vertx.setTimer(random.nextInt(5000) + 1000, this::update);
  }

  private void update(long timerId) {
    temperature = temperature + (delta()/10);
    JsonObject payload = new JsonObject()
      .put("id", sensorId)
      .put("temp", temperature);
    vertx.eventBus().publish("sensor.updates", payload);
    scheduleNextUpdate();
  }

  private double delta() {
    if(random.nextInt() > 0) {
      return random.nextGaussian();
    } else {
      return -random.nextGaussian();
    }
  }
}
