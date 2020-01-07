package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WorkerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.setPeriodic(10_000, id -> {
            try {
                log.info("Zzz...");
                TimeUnit.SECONDS.sleep(8);
                log.info("Up!");
            } catch(InterruptedException e) {
                log.error("Woops", e);
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setInstances(2);
        deploymentOptions.setWorker(true);
        vertx.deployVerticle("chapter2.hello.WorkerVerticle", deploymentOptions);
    }
}
