package ru.otus.spring.homework.spring11;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.spring.homework.spring11.utils.RawResultPrinterImpl;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({
        "ru.otus.spring.homework.spring11.changelogs",
        "ru.otus.spring.homework.spring11.repositories",
        "ru.otus.spring.homework.spring11.config"})
@Import(RawResultPrinterImpl.class)
public abstract class AbstractRepositoryTest {
}
