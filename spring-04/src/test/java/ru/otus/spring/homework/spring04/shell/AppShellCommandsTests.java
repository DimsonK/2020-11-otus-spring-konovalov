package ru.otus.spring.homework.spring04.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import ru.otus.spring.homework.spring04.Main;
import ru.otus.spring.homework.spring04.domain.Person;
import ru.otus.spring.homework.spring04.service.QuestionService;
import ru.otus.spring.homework.spring04.service.LocaleService;
import ru.otus.spring.homework.spring04.service.StudentTestService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("тест команд shell")
@SpringBootTest(classes = Main.class)
public class AppShellCommandsTests {
    private static final Logger log = LoggerFactory.getLogger(AppShellCommandsTests.class);

    private Person person;

    @MockBean
    private StudentTestService testService;

    @MockBean
    private LocaleService localeService;

    @MockBean QuestionService fileReader;

    @Autowired
    private Shell shell;

    @Autowired
    private MessageSource messageSource;

    private static final String COMMAND_REGISTER = "register";
    private static final String COMMAND_REGISTER_SHORT = "r";
    private static final String GREETING_REGISTER =
            "Поздравляем. Вы зарегистрированы. Теперь Вы можете пройти тест нажав: t (test)";
    private static final String COMMAND_TEST = "test";
    private static final String COMMAND_TEST_SHORT = "t";

    @TestConfiguration
    static class AppShellCommandsTestsConfig {
        @Bean
        public MessageSource messageSource() {
            var ms = new ReloadableResourceBundleMessageSource();
            ms.setBasename("classpath:/i18n/bundle");
            ms.setDefaultEncoding("UTF-8");
            return ms;
        }
    }

    @DisplayName("должен возвращать форму приветствия после регистрации пользователя")
    @Test
    void mustReturnTheWelcomeFormAfterUserRegistration() {
        String res = (String) shell.evaluate(() -> COMMAND_REGISTER);
        assertEquals(GREETING_REGISTER, res);

        res = (String) shell.evaluate(() -> COMMAND_REGISTER_SHORT);
        assertEquals(GREETING_REGISTER, res);
    }

    @DisplayName("должен возвращать ошибку, т.к. пользователь не зарегистрирован")
    @Test
    void mustReturnTheWelcomeFormAfterUserTesting() {
        Object res = shell.evaluate(() -> COMMAND_TEST);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);

        res = shell.evaluate(() -> COMMAND_TEST_SHORT);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }


}
