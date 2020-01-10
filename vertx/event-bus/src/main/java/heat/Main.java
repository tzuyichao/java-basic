package heat;

import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("heat.HeatSensor");
        vertx.deployVerticle("heat.Listener");
        vertx.deployVerticle("heat.SensorData");
        vertx.deployVerticle("heat.AverageRequester");
    }
}
