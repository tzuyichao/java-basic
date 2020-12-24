package heat;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        DeploymentOptions heatSensorDeploymentOptions = new DeploymentOptions()
                .setInstances(10);

        vertx.deployVerticle("heat.HeatSensor", heatSensorDeploymentOptions);
        vertx.deployVerticle("heat.Listener");
        vertx.deployVerticle("heat.SensorData");
        vertx.deployVerticle("heat.HttpServer");
    }
}
