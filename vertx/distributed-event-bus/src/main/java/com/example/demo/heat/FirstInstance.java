package com.example.demo.heat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirstInstance {
  private static DeploymentOptions getHeatSensorDeploymentOptions() {
    return new DeploymentOptions().setInstances(4);
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("com.example.demo.heat.HeatSensor", getHeatSensorDeploymentOptions());
    vertx.deployVerticle("com.example.demo.heat.HttpServer");
  }
}
