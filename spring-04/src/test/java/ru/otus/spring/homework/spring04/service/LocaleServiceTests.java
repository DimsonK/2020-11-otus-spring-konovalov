package ru.otus.spring.homework.spring04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.homework.spring04.config.AppProp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("LocaleService tests")
@SpringBootTest
class LocaleServiceTests {
    private static final Logger log = LoggerFactory.getLogger(LocaleServiceTests.class);

    @Autowired
    AppProp prop;

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        LocaleService ls = new LocaleServiceImpl(prop);
        assertFalse(prop.getLang().isUseSystemLang());
        assertEquals("ru_RU", ls.getLocale().toString());
    }
}
