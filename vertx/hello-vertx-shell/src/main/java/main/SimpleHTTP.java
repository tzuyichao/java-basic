package main;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.HttpTermOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleHTTP {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ShellService shellService = ShellService.create(vertx, new ShellServiceOptions()
                .setHttpOptions(new HttpTermOptions()
                        .setHost("localhost")
                        .setPort(8080)
                        .setAuthOptions(new ShiroAuthOptions().setType(ShiroAuthRealmType.PROPERTIES).setConfig(new JsonObject().put("properties_path", "file:auth.properties")))
                )
        );
        log.info("Starting HTTP Shell Service...");
        shellService.start(asyncResult -> {
            if(asyncResult.succeeded()) {
                log.info("completed.");
            } else {
                log.info("failed.");
            }
        });
    }
}
