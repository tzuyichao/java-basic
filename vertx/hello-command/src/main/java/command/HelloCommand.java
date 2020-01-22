package command;

import io.vertx.ext.shell.command.AnnotatedCommand;
import io.vertx.ext.shell.command.CommandProcess;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloCommand extends AnnotatedCommand {
    private String name;

    public HelloCommand() {
        this.name = "hello";
    }

    @Override
    public void process(CommandProcess process) {
        process.write("Hello, World!\n");
    }
}
