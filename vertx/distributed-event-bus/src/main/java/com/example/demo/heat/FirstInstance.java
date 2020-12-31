package com.example.demo.heat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstInstance {
  private static DeploymentOptions getHeatSensorDeploymentOptions() {
    return new DeploymentOptions().setInstances(4);
  }

  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), ar -> {
      if(ar.succeeded()) {
        Vertx vertx = ar.result();
        vertx.deployVerticle("com.example.demo.heat.HeatSensor", getHeatSensorDeploymentOptions());
        vertx.deployVerticle("com.example.demo.heat.HttpServer");
      } else {
        log.error("Create Clustered Vertx failed", ar.cause());
      }
    });
  }
}
