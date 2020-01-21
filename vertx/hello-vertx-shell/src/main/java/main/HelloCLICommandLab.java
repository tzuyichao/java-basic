package main;

import io.vertx.core.Vertx;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
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
public class HelloCLICommandLab {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        CLI helloCli = CLI.create("hello")
                .addArgument(new Argument().setArgName("my-arg"))
                .addOption(new Option().setShortName("m").setLongName("my-option"));
        CommandBuilder builder = CommandBuilder.command(helloCli);
        builder.processHandler(process -> {
            process.write("Hello, World!\n");
            CommandLine commandLine = process.commandLine();
            String argumentValue = commandLine.getArgumentValue(0);
            String optionValue = commandLine.getOptionValue("my-option");
            process.write(String.format("The argument is %s and the option is %s\n", argumentValue, optionValue));
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
