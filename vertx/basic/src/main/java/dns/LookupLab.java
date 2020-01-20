package dns;

import io.vertx.core.Vertx;
import io.vertx.core.dns.DnsClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LookupLab {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DnsClient client = vertx.createDnsClient(53, "8.8.8.8");
        client.lookup("vertx.io", stringAsyncResult -> {
            if(stringAsyncResult.succeeded()) {
                log.info("Result: {}", stringAsyncResult.result());
            } else {
                log.error("Failed to resolve entry {}", stringAsyncResult.cause());
            }
            vertx.close(asyncResult -> {
                if(asyncResult.succeeded()) {
                    log.info("vert.x shutdown success");
                } else {
                    log.error("vert.x shutdown failed {}", asyncResult.cause());
                }
            });
        });
    }
}
