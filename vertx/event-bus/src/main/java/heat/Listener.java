package heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class Listener extends AbstractVerticle {
    private final DecimalFormat format = new DecimalFormat("#.##");

    @Override
    public void start() throws Exception {
        EventBus eventBus = vertx.eventBus();
        eventBus.<JsonObject>consumer("sensor.updates", msg -> {
            JsonObject body = msg.body();
            String id = body.getString("id");
            String temp = format.format(body.getDouble("temp"));
            log.info("{} reports a temperature ~{}C", id, temp);
        });
    }
}
