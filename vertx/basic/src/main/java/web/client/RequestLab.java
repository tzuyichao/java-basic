package web.client;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestLab {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        client.get(8080, "localhost", "/products")
                .send(asyncResult -> {
                    if(asyncResult.succeeded()) {
                        HttpResponse<Buffer> response = asyncResult.result();
                        log.info("status code: {}", response.statusCode());
                        log.info("body: {}", response.bodyAsString());
                    } else {
                        log.error("something wrong {}", asyncResult.cause());
                    }
                    vertx.close();
                });
    }
}
