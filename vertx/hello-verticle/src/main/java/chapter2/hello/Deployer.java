package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Deployer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        log.info("deployer start");
        long delay = 1000;
        for(int i=0; i<50; i++) {
            vertx.setTimer(delay, id -> {
                deploy();
            });
            delay += 1000;
        }
    }

    private void deploy() {
        vertx.deployVerticle(new EmptyVerticle(), ar -> {
            if(ar.succeeded()) {
                String id = ar.result();
                log.info("Successfully deploy {}", id);
                vertx.setTimer(5000, tid -> undeployLater(id));
            } else {
                log.error("Error while deploying: {}", ar.cause());
            }
        });
    }

    private void undeployLater(String id) {
        vertx.undeploy(id, ar -> {
            if(ar.succeeded()) {
                log.info("{} was undeployed", id);
            } else {
                log.error("{} could not be undeployed", id);
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Deployer());
    }
}
