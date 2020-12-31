package com.example.demo.heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class SensorData extends AbstractVerticle {
  private final Map<String, Double> lastValues = new HashMap<>();

  @Override
  public void start() throws Exception {
    EventBus eventBus = vertx.eventBus();
    eventBus.consumer("sensor.updates", this::update);
    eventBus.consumer("sensor.average", this::average);
  }

  private void update(Message<JsonObject> message) {
    JsonObject data = message.body();
    lastValues.put(data.getString("id"), data.getDouble("temp"));
  }

  private void average(Message<JsonObject> message) {
    double avg = lastValues.values().stream().collect(Collectors.averagingDouble(Double::doubleValue));
    JsonObject json = new JsonObject().put("average", avg);
    message.reply(json);
  }
}
