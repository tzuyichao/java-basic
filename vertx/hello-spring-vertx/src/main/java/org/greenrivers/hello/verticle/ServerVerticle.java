package org.greenrivers.hello.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServerVerticle extends AbstractVerticle {
    private Integer defaultPort = 8088;

    private void hello(RoutingContext routingContext) {
        log.info("hello called");
        routingContext.response()
                .setStatusCode(200)
                .end("Hello, World!");
    }

    private void getAllDocuments(RoutingContext routingContext) {
        log.info("get all documents");
        vertx.eventBus()
                .<String>request(DocumentVerticle.GET_ALL_DOCUMENTS, "", result -> {
                    if(result.succeeded()) {
                        routingContext.response()
                                .putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .setStatusCode(HttpStatus.OK.value())
                                .end(result.result().body());
                    } else {
                        routingContext.response()
                                .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .end();
                    }
                });
    }

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        router.get("/hello")
                .handler(this::hello);
        router.get("/document")
                .handler(this::getAllDocuments);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", defaultPort));
        log.info("Running...");
    }
}
