package ru.otus.spring.homework.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.homework.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test application properties")
@SpringBootTest
@ContextConfiguration(classes = Main.class)
class AppPropTests {

    @DisplayName("chek lang load")
    @Test
    void checkLangExists(@Autowired AppProp prop) {
        assertEquals("ru_RU", prop.getLang().getDefLang().toString());
    }
}
