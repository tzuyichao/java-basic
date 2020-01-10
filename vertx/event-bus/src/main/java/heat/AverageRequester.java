package heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AverageRequester extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.setPeriodic(10000, id -> {
            EventBus eventBus = vertx.eventBus();
            eventBus.<JsonObject>request("sensor.average", "", reply -> {
                if(reply.succeeded()) {
                    log.info("data: {}", reply.result().body());
                }
            });
        });
    }
}
