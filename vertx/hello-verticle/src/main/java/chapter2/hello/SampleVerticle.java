package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleVerticle extends AbstractVerticle {
    private static final String N = "n";
    @Override
    public void start() throws Exception {
        log.info("n={}", config().getInteger(N, -1));
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        for(int i=0; i<4; i++) {
            JsonObject conf = new JsonObject().put(N, i);
            DeploymentOptions deploymentOptions = new DeploymentOptions();
            deploymentOptions.setConfig(conf);
            deploymentOptions.setInstances(i); // Set the number of instances that should be deployed.
            log.info("deploy {}", i);
            vertx.deployVerticle("chapter2.hello.SampleVerticle", deploymentOptions);
        }
        log.info("{}", vertx.deploymentIDs());
    }
}
