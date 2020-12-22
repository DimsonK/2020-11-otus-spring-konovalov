package ru.otus.spring.homework.spring04.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Person class test")
class PersonTests {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Person person = new Person("FirstName", "LastName");

        assertAll("person",
                () -> assertEquals("FirstName", person.getFirstName()),
                () -> assertEquals("LastName", person.getLastName()));
        assertNotNull(person.getTestResultList());

        TestResult result = new TestResult("1", "2", false);
        person.addTestResult(result);

        assertEquals(1, person.getTestResultList().size());
    }
}
