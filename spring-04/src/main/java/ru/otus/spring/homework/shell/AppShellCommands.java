package ru.otus.spring.homework.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.homework.domain.Person;
import ru.otus.spring.homework.service.LocaleService;
import ru.otus.spring.homework.service.StudentTestService;

@ShellComponent
public class AppShellCommands {
    private static final Logger log = LoggerFactory.getLogger(AppShellCommands.class);

    private Person person;
    private final StudentTestService studentTestService;
    private final MessageSource messageSource;
    private final LocaleService localeService;

    public AppShellCommands(
            StudentTestService studentTestService,
            MessageSource messageSource,
            LocaleService localeService) {
        this.studentTestService = studentTestService;
        this.messageSource = messageSource;
        this.localeService = localeService;
    }

    @ShellMethod(value = "Регистрация студента", key = {"r", "register"})
    public String login() {
        this.person = studentTestService.register();
        return messageSource.getMessage("shell.register.success", null, localeService.getLocale());
    }

    @ShellMethod(value = "Запуск тестирования", key = {"t", "test"})
    @ShellMethodAvailability(value = "isLoggedIn")
    public String runTest() {
        this.person = studentTestService.runTest(person);
        return messageSource.getMessage("shell.test.finished", null, localeService.getLocale());
    }

    @ShellMethod(value = "Печать результатов", key = {"p", "print"})
    @ShellMethodAvailability(value = "isTested")
    public void printResult() {
        studentTestService.printTestResult(person);
    }

    private Availability isLoggedIn() {
        return person == null ? Availability.unavailable(
                messageSource.getMessage("shell.not.register", null, localeService.getLocale())
        ) : Availability.available();
    }

    private Availability isTested() {
        if (person == null) {
            return Availability.unavailable(
                    messageSource.getMessage("shell.not.register", null, localeService.getLocale())
            );
        } else if (person.getTestResultList().isEmpty()) {
            return Availability.unavailable(
                    messageSource.getMessage("shell.not.tested", null, localeService.getLocale())
            );
        } else {
            return Availability.available();
        }
    }
}
