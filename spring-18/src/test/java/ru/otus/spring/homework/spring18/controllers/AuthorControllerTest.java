package ru.otus.spring.homework.spring18.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework.spring18.models.dto.AuthorDto;
import ru.otus.spring.homework.spring18.repositories.UserRepository;
import ru.otus.spring.homework.spring18.security.AuthProvider;
import ru.otus.spring.homework.spring18.service.AuthorService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер авторов должен")
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    private static final String REQUEST_JSON_ALL = "[{\"id\":\"1\",\"name\":\"Agatha Christie\"},{\"id\":\"2\",\"name\":\"Alexandre Dumas\"},{\"id\":\"3\",\"name\":\"Jules Gabriel Verne\"},{\"id\":\"4\",\"name\":\"Edgar Allan Poe\"},{\"id\":\"5\",\"name\":\"Stephen Edwin King\"}]";
    private static final String REQUEST_JSON_ONE = "{\"id\":\"1\",\"name\":\"Agatha Christie\"}";
    private static final String REQUEST_JSON_ADD = "{\"id\":\"10\",\"name\":\"Veniamin\"}";
    private static final String REQUEST_JSON_UPDATE = "{\"id\":\"1\",\"name\":\"Veniamin Dorofeev\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthProvider authProvider;
    @MockBean
    private AuthorService service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("возвращать всех авторов")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getAuthors() throws Exception {
        var authors = List.of(
                new AuthorDto("1", "Agatha Christie"),
                new AuthorDto("2", "Alexandre Dumas"),
                new AuthorDto("3", "Jules Gabriel Verne"),
                new AuthorDto("4", "Edgar Allan Poe"),
                new AuthorDto("5", "Stephen Edwin King")
        );
        Mockito.when(service.getAll()).thenReturn(authors);
        this.mockMvc
                .perform(get("/api/author"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ALL));
    }

    @DisplayName("возвращать автора по его ID")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void getAuthor() throws Exception {
        var author = new AuthorDto("1", "Agatha Christie");
        Mockito.when(service.getAuthor(1L)).thenReturn(author);
        this.mockMvc
                .perform(get("/api/author/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ONE));
    }

    @DisplayName("добавлять автора")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void addAuthor() throws Exception {
        var newAuthor = new AuthorDto("0", "Veniamin");
        var actualAuthor = new AuthorDto("10", "Veniamin");
        Mockito.when(service.addAuthor(newAuthor.getName())).thenReturn(actualAuthor);
        this.mockMvc
                .perform(post("/api/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newAuthor)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ADD));
    }

    @DisplayName("изменять автора")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void updateAuthor() throws Exception {
        // Add Author
        var actualAuthor = new AuthorDto("1", "Agatha Christie");
        // Update Author
        var updatedAuthor = new AuthorDto("1", "Veniamin Dorofeev");
        Mockito.when(service.getAuthor(1L)).thenReturn(actualAuthor);
        Mockito.when(service.updateAuthor(updatedAuthor)).thenReturn(updatedAuthor);
        this.mockMvc
                .perform(put("/api/author/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedAuthor)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_UPDATE));
    }

    @DisplayName("удалять автора")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void deleteAuthor() throws Exception {
        var actualAuthor = new AuthorDto("1", "Agatha Christie");
        Mockito.when(service.getAuthor(1L)).thenReturn(actualAuthor);
        // Delete Author
        this.mockMvc
                .perform(delete("/api/author/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}