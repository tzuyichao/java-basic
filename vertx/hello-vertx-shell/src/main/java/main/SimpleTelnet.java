package main;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.shell.ShellService;
import io.vertx.ext.shell.ShellServiceOptions;
import io.vertx.ext.shell.term.TelnetTermOptions;

public class SimpleTelnet {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        ShellService shellService = ShellService.create(vertx, new ShellServiceOptions().setTelnetOptions(new TelnetTermOptions().setHost("localhost").setPort(4000)));
        shellService.start();
    }
}
