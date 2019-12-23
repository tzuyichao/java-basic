package verticle.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainVerticle extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutdown vert.x");
            vertx.close();
        }));
    }

    @Override
    public void start() throws Exception {
        log.info("[Main] Running in {}", Thread.currentThread().getName());

        vertx.deployVerticle("verticle.worker.WorkerVerticle", new DeploymentOptions().setWorker(true));

        // send(String address, @Nullable Object message, Handler<AsyncResult<Message<T>>> replyHandler) is Deprecated
        vertx.eventBus().request("sample.data", "hello vert.x", receiver -> {
            log.info("[Main] Received {} in {}", receiver.result().body(), Thread.currentThread().getName());
        });
    }
}
