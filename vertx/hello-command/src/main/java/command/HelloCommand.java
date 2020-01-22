package command;

import io.vertx.ext.shell.command.AnnotatedCommand;
import io.vertx.ext.shell.command.CommandProcess;

public class HelloCommand extends AnnotatedCommand {
    @Override
    public void process(CommandProcess process) {
        process.write("Hello, World!\n");
    }
}
