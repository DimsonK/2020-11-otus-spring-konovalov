package ru.otus.spring.homework.springproject.controllers;

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
import ru.otus.spring.homework.springproject.models.dto.AuthorDto;
import ru.otus.spring.homework.springproject.models.dto.BookDto;
import ru.otus.spring.homework.springproject.models.dto.GenreDto;
import ru.otus.spring.homework.springproject.repositories.UserRepository;
import ru.otus.spring.homework.springproject.security.AuthProvider;
import ru.otus.spring.homework.springproject.service.BookService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер книг должен")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final String REQUEST_JSON_ALL = "[{\"id\":\"1\",\"name\":\"Murder on the Orient Express\",\"author\":{\"id\":\"1\",\"name\":\"Agatha Christie\"},\"genres\":[{\"id\":\"1\",\"name\":\"Detective\"}],\"comments\":[]},{\"id\":\"2\",\"name\":\"The Three Musketeers\",\"author\":{\"id\":\"2\",\"name\":\"Alexandre Dumas\"},\"genres\":[{\"id\":\"2\",\"name\":\"History\"}],\"comments\":[]},{\"id\":\"3\",\"name\":\"Twenty Thousand Leagues Under the Sea\",\"author\":{\"id\":\"3\",\"name\":\"Jules Gabriel Verne\"},\"genres\":[{\"id\":\"3\",\"name\":\"Fantasy\"}],\"comments\":[]},{\"id\":\"4\",\"name\":\"The Gold Bug\",\"author\":{\"id\":\"4\",\"name\":\"Edgar Allan Poe\"},\"genres\":[{\"id\":\"3\",\"name\":\"Fantasy\"}],\"comments\":[]},{\"id\":\"5\",\"name\":\"It\",\"author\":{\"id\":\"5\",\"name\":\"Stephen Edwin King\"},\"genres\":[{\"id\":\"4\",\"name\":\"Horror\"}],\"comments\":[]}]";
    private static final String REQUEST_JSON_ONE_ARRAY = "[{\"id\":\"3\",\"name\":\"Twenty Thousand Leagues Under the Sea\",\"author\":{\"id\":\"3\",\"name\":\"Jules Gabriel Verne\"},\"genres\":[{\"id\":\"3\",\"name\":\"Fantasy\"}],\"comments\":[]}]";
    private static final String REQUEST_JSON_ONE = "{\"id\":\"3\",\"name\":\"Twenty Thousand Leagues Under the Sea\",\"author\":{\"id\":\"3\",\"name\":\"Jules Gabriel Verne\"},\"genres\":[{\"id\":\"3\",\"name\":\"Fantasy\"}],\"comments\":[]}";
    private static final String REQUEST_JSON_ADD = "{\"id\":\"10\",\"name\":\"The ABC Murders\",\"author\":{\"id\":\"1\",\"name\":\"Agatha Christie\"},\"genres\":[{\"id\":\"1\",\"name\":\"Detective\"}],\"comments\":[]}";
    private static final String REQUEST_JSON_UPDATE = "{\"id\":\"1\",\"name\":\"Murder Made Easy\",\"author\":{\"id\":\"1\",\"name\":\"Agatha Christie\"},\"genres\":[{\"id\":\"1\",\"name\":\"Detective\"}],\"comments\":[]}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthProvider authProvider;
    @MockBean
    private BookService service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("возвращать все книги")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getBooks() throws Exception {
        var expectedBookList = List.of(
                new BookDto("1", "Murder on the Orient Express", 12, 2,
                        new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of()),
                new BookDto("2", "The Three Musketeers", 6, 2,
                        new AuthorDto("2", "Alexandre Dumas"), List.of(new GenreDto("2", "History")), List.of()),
                new BookDto("3", "Twenty Thousand Leagues Under the Sea", 6, 2,
                        new AuthorDto("3", "Jules Gabriel Verne"), List.of(new GenreDto("3", "Fantasy")), List.of()),
                new BookDto("4", "The Gold Bug", 6, 2,
                        new AuthorDto("4", "Edgar Allan Poe"), List.of(new GenreDto("3", "Fantasy")), List.of()),
                new BookDto("5", "It", 16, 2,
                        new AuthorDto("5", "Stephen Edwin King"), List.of(new GenreDto("4", "Horror")), List.of()));
        Mockito.when(service.getAll()).thenReturn(expectedBookList);
        this.mockMvc
                .perform(get("/api/book"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ALL));
    }

    @DisplayName("возвращать книги по подстроке")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getBooksByPartOfName() throws Exception {
        var expectedBookList = List.of(
                new BookDto("3", "Twenty Thousand Leagues Under the Sea", 6, 2,
                        new AuthorDto("3", "Jules Gabriel Verne"), List.of(new GenreDto("3", "Fantasy")), List.of())
        );
        Mockito.when(service.getBooksLikeName("Thousand")).thenReturn(expectedBookList);
        this.mockMvc
                .perform(get("/api/book?search=Thousand"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ONE_ARRAY));
    }


    @DisplayName("возвращать книгу по ID")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getBook() throws Exception {
        var expectedBook = new BookDto("3", "Twenty Thousand Leagues Under the Sea", 6, 2,
                new AuthorDto("3", "Jules Gabriel Verne"), List.of(new GenreDto("3", "Fantasy")), List.of());
        Mockito.when(service.getBook(3L)).thenReturn(expectedBook);
        this.mockMvc
                .perform(get("/api/book/3"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ONE));
    }

    @DisplayName("добавлять книгу")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void addBook() throws Exception {
        var newBook = new BookDto("0", "The ABC Murders", 16, 2,
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        var actualBook = new BookDto("10", "The ABC Murders", 16, 2,
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(service.addBook(newBook)).thenReturn(actualBook);
        this.mockMvc
                .perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newBook)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ADD));
    }

    @DisplayName("изменять книгу")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void updateBook() throws Exception {
        var originalBook = new BookDto("1", "Murder on the Orient Express", 12, 2,
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        var updatedBook = new BookDto("1", "Murder Made Easy", 12, 2,
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(service.getBook(Long.parseLong(originalBook.getId()))).thenReturn(originalBook);
        Mockito.when(service.updateBook(updatedBook)).thenReturn(updatedBook);
        this.mockMvc
                .perform(put("/api/book/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedBook)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_UPDATE));
    }

    @DisplayName("удалять книгу")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void deleteBook() throws Exception {
        var actualBook = new BookDto("1", "Murder on the Orient Express", 12, 2,
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(service.getBook(1L)).thenReturn(actualBook);
        // Delete Author
        this.mockMvc
                .perform(delete("/api/book/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}