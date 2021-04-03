package ru.otus.spring.homework.spring16.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework.spring16.models.dto.AuthorDto;
import ru.otus.spring.homework.spring16.models.entity.Author;
import ru.otus.spring.homework.spring16.service.AuthorService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthors() {
        try {
            this.mockMvc.perform(get("/authors")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}