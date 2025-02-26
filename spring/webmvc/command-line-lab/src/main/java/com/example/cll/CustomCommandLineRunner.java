package com.example.cll;

import com.example.cll.core.ExecCommand;
import com.example.cll.core.ResponseFor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {
    private final ApplicationContext context;

    public CustomCommandLineRunner(ApplicationContext context) {
        this.context = context;
    }
    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            System.out.println("No command provided. Starting in web mode...");
            return;
        }

        String command = args[0];
        Map<String, ExecCommand> commands = getCommands();
        ExecCommand execCommand = commands.get(command);

        if (execCommand != null) {
            System.out.println("Executing command: " + command);
            execCommand.run(args);
        } else {
            System.out.println("Unknown command: " + command);
        }

        System.exit(0);
    }

    private Map<String, ExecCommand> getCommands() {
        Map<String, ExecCommand> commands = new HashMap<>();
        Map<String, Object> beans = context.getBeansWithAnnotation(ResponseFor.class);

        for (Object bean : beans.values()) {
            ResponseFor annotation = bean.getClass().getAnnotation(ResponseFor.class);
            commands.put(annotation.value(), (ExecCommand) bean);
        }

        return commands;
    }
}
