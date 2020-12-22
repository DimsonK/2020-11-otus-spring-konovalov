package ru.otus.spring.homework.spring04.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ResourceReaderCsvTest class test")
@SpringBootTest(classes = ResourceReaderCsv.class)
class ResourceReaderCsvTest {

    @Autowired
    ResourceReaderCsv resourceReaderCsv;

    @DisplayName("читает ресурс")
    @Test
    void getResource() {
        List<String[]> resourceList = resourceReaderCsv.getResource("tests/questions_en.csv");
        assertThat(resourceList).isNotNull().contains(new String[]{"1", "what color is the USSR flag"});
    }
}