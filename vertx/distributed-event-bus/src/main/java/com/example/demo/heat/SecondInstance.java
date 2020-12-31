package com.example.demo.heat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecondInstance {
  private static DeploymentOptions getHeatSensorDeploymentOptions() {
    return new DeploymentOptions().setInstances(4);
  }

  private static DeploymentOptions getHttpServerDeploymentOptions() {
    JsonObject conf = new JsonObject().put("port", 8081);
    return new DeploymentOptions().setConfig(conf);
  }

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), ar -> {
      if(ar.succeeded()) {
        Vertx vertx = ar.result();
        vertx.deployVerticle("com.example.demo.heat.HeatSensor", getHeatSensorDeploymentOptions());
        vertx.deployVerticle("com.example.demo.heat.Listener");
        vertx.deployVerticle("com.example.demo.heat.SensorData");
        vertx.deployVerticle("com.example.demo.heat.HttpServer", getHttpServerDeploymentOptions());
      } else {
        log.error("Create Clustered Vertx failed", ar.cause());
      }
    });
  }
}
