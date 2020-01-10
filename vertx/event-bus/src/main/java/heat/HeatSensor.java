package heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

import java.util.Random;
import java.util.UUID;

public class HeatSensor extends AbstractVerticle {
    private final Random random = new Random();
    private final String id = UUID.randomUUID().toString();
    private double temp = 21.0;

    @Override
    public void start() throws Exception {
        scheduleNextUpdate();
    }

    private void scheduleNextUpdate() {
        vertx.setTimer(random.nextInt(5000) + 1000, this::update);
    }

    private void update(long tid) {
        temp = temp + (delta()/10);
        JsonObject payload = new JsonObject();
        payload.put("id", id);
        payload.put("temp", temp);
        vertx.eventBus().publish("sensor.updates", payload);
        scheduleNextUpdate();
    }

    private double delta() {
        if(random.nextInt()> 0) {
            return random.nextGaussian();
        } else {
            return -random.nextGaussian();
        }
    }
}
