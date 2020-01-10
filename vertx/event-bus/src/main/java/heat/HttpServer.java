package heat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer()
                .requestHandler(this::handler)
                .listen(config().getInteger("port", 8088));
    }

    private void handler(HttpServerRequest request) {
        if("/".equals(request.path())) {
            request.response().sendFile("index.html");
        }
    }
}
