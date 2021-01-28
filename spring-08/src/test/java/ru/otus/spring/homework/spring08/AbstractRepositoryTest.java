package ru.otus.spring.homework.spring08;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.otus.spring.homework.spring08.utils.RawResultPrinterImpl;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring.homework.spring08.repositories"})
@Import(RawResultPrinterImpl.class)
public abstract class AbstractRepositoryTest {
}
