package main;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import io.vertx.ext.shell.ShellServer;
import io.vertx.ext.shell.command.CommandResolver;
import io.vertx.ext.shell.term.HttpTermOptions;
import io.vertx.ext.shell.term.SSHTermOptions;
import io.vertx.ext.shell.term.TermServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShellServerLab {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ShellServer shellServer = ShellServer.create(vertx);

        TermServer httpTermServer = TermServer.createHttpTermServer(vertx, new HttpTermOptions()
                .setHost("localhost").setPort(8080)
                .setAuthOptions(new ShiroAuthOptions()
                        .setType(ShiroAuthRealmType.PROPERTIES)
                        .setConfig(new JsonObject().put("properties_path", "file:auth.properties"))));
        TermServer sshTermServer = TermServer.createSSHTermServer(vertx, new SSHTermOptions().setHost("localhost").setPort(5000)
                .setKeyPairOptions(new JksOptions().setPath("ssh.jks").setPassword("secret"))
                .setAuthOptions(new ShiroAuthOptions()
                        .setType(ShiroAuthRealmType.PROPERTIES)
                        .setConfig(new JsonObject().put("properties_path", "file:auth.properties"))));

        shellServer.registerTermServer(httpTermServer);
        shellServer.registerTermServer(sshTermServer);

        shellServer.registerCommandResolver(CommandResolver.baseCommands(vertx));
        log.info("Shell Server Starting...");
        shellServer.listen(asyncResult -> {
            if(asyncResult.succeeded()) {
                log.info("done.");
            } else {
                log.info("failed. {}", asyncResult.cause());
            }
        });
    }
}
