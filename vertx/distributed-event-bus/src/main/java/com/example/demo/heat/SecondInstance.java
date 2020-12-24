package com.example.demo.heat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class SecondInstance {
  private static DeploymentOptions getHeatSensorDeploymentOptions() {
    return new DeploymentOptions().setInstances(4);
  }

  private static DeploymentOptions getHttpServerDeploymentOptions() {
    JsonObject conf = new JsonObject().put("port", 8081);
    return new DeploymentOptions().setConfig(conf);
  }

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle("com.example.demo.heat.HeatSensor", getHeatSensorDeploymentOptions());
    vertx.deployVerticle("com.example.demo.heat.HttpServer", getHttpServerDeploymentOptions());
  }
}
