package ru.otus.spring.homework.spring10.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework.spring10.models.dto.AuthorDto;
import ru.otus.spring.homework.spring10.models.dto.GenreDto;
import ru.otus.spring.homework.spring10.service.AuthorService;
import ru.otus.spring.homework.spring10.service.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер жанров должен")
@Slf4j
@WebMvcTest(GenreController.class)
class GenreControllerTest {

    private static final String REQUEST_JSON_ALL = "[{\"id\":\"1\",\"name\":\"Detective\"},{\"id\":\"2\",\"name\":\"History\"},{\"id\":\"3\",\"name\":\"Fantasy\"},{\"id\":\"4\",\"name\":\"Horror\"}]";
    private static final String REQUEST_JSON_ONE = "{\"id\":\"1\",\"name\":\"Detective\"}";
    private static final String REQUEST_JSON_ADD = "{\"id\":\"10\",\"name\":\"Novella\"}";
    private static final String REQUEST_JSON_UPDATE = "{\"id\":\"1\",\"name\":\"Novella\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("возвращать все жанры")
    @Test
    void getGenres() throws Exception {
        var genres = List.of(
                new GenreDto("1", "Detective"),
                new GenreDto("2", "History"),
                new GenreDto("3", "Fantasy"),
                new GenreDto("4", "Horror")
        );
        Mockito.when(service.getAll()).thenReturn(genres);
        this.mockMvc
                .perform(get("/api/genre"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ALL));
    }

    @DisplayName("возвращать жанр по его ID")
    @Test
    void testGetGenres() throws Exception {
        var genre = new GenreDto("1", "Detective");
        Mockito.when(service.getGenre(1L)).thenReturn(genre);
        this.mockMvc
                .perform(get("/api/genre/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ONE));
    }

    @DisplayName("добавлять жанр")
    @Test
    void addGenre() throws Exception {
        var newGenre = new GenreDto("0", "Novella");
        var actualGenre = new GenreDto("10", "Novella");
        Mockito.when(service.addGenre(newGenre.getName())).thenReturn(actualGenre);
        this.mockMvc
                .perform(post("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newGenre)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ADD));
    }

    @DisplayName("изменять жанр")
    @Test
    void updateGenre() throws Exception {
        var genre = new GenreDto("1", "Detective");
        var updatedGenre = new GenreDto("1", "Novella");
        Mockito.when(service.getGenre(1L)).thenReturn(genre);
        Mockito.when(service.updateGenre(updatedGenre)).thenReturn(updatedGenre);
        this.mockMvc
                .perform(put("/api/genre/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedGenre)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_UPDATE));
    }

    @DisplayName("удалять жанр")
    @Test
    void deleteGenre() throws Exception {
        var genre = new GenreDto("1", "Detective");
        Mockito.when(service.getGenre(1L)).thenReturn(genre);
        // Delete Author
        this.mockMvc
                .perform(delete("/api/genre/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}