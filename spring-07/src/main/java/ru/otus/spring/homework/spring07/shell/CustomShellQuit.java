package ru.otus.spring.homework.spring07.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
@ShellCommandGroup("Quit")
public class CustomShellQuit implements Quit.Command {

    private final ApplicationContext appContext;

    public CustomShellQuit(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @ShellMethod(value = "Exit from application", key = {"quit", "exit"})
    public void quit() {
        int exitCode = SpringApplication.exit(appContext, () -> 0);
        System.exit(exitCode);
    }
}
