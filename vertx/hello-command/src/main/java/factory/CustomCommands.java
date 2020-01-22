package factory;

import command.HelloCommand;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.shell.command.Command;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandResolver;
import io.vertx.ext.shell.spi.CommandResolverFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomCommands implements CommandResolverFactory {
    @Override
    public void resolver(Vertx vertx, Handler<AsyncResult<CommandResolver>> resolveHandler) {
        List<Command> commands = new ArrayList<>();

        // Add commands
        commands.add(Command.create(vertx, HelloCommand.class));

        // Add another command
        commands.add(CommandBuilder.command("info").processHandler(process -> {
            process.write("custom command info.\n");
        }).build(vertx));

        // Resolve with the commands
        resolveHandler.handle(Future.succeededFuture(() -> commands));
    }
}
