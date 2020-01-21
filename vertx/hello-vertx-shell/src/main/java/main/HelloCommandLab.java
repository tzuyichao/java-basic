package main;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.ext.shell.term.SSHTermOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloCommandLab {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        CommandBuilder builder = CommandBuilder.command("hello");
        builder.processHandler(process -> {
            process.write("Hello, World!\n");
            for(String arg : process.args()) {
                process.write(String.format("Argument: %s\n", arg));
            }
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));

        ShellService shellService = ShellService.create(vertx, new ShellServiceOptions()
                .setSSHOptions(new SSHTermOptions().setHost("localhost").setPort(5000)
                        .setKeyPairOptions(new JksOptions().setPath("ssh.jks").setPassword("secret"))
                        .setAuthOptions(new ShiroAuthOptions()
                                .setType(ShiroAuthRealmType.PROPERTIES)
                                .setConfig(new JsonObject().put("properties_path", "file:auth.properties")))
                )
        );
        log.info("Starting ssh server...");
        shellService.start();
    }
}
