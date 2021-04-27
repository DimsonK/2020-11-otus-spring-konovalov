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
import ru.otus.spring.homework.springproject.models.dto.CommentDto;
import ru.otus.spring.homework.springproject.repositories.UserRepository;
import ru.otus.spring.homework.springproject.security.AuthProvider;
import ru.otus.spring.homework.springproject.service.CommentService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Контроллер комментариев должен")
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private static final String REQUEST_JSON_ALL = "[{\"id\":\"1\",\"postDate\":\"01.01.2020\",\"authorName\":\"Author1\",\"content\":\"Comment content 1\",\"favorite\":true,\"bookId\":\"1\"},{\"id\":\"2\",\"postDate\":\"02.02.2020\",\"authorName\":\"Author2\",\"content\":\"Comment content 2\",\"favorite\":false,\"bookId\":\"1\"}]";
    private static final String REQUEST_JSON_ONE = "{\"id\":\"2\",\"postDate\":\"02.02.2020\",\"authorName\":\"Author2\",\"content\":\"Comment content 2\",\"favorite\":false,\"bookId\":\"1\"}";
    private static final String REQUEST_JSON_ADD = "{\"id\":\"1\",\"postDate\":\"05.05.2021\",\"authorName\":\"Author1\",\"content\":\"Comment content 3\",\"favorite\":true,\"bookId\":\"1\"}";
    private static final String REQUEST_JSON_UPDATE = "{\"id\":\"1\",\"postDate\":\"10.10.2021\",\"authorName\":\"Author3\",\"content\":\"Comment content 5\",\"favorite\":false,\"bookId\":\"1\"}";
    ;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthProvider authProvider;
    @MockBean
    private CommentService service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("возвращать все комментарии для книги")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getComments() throws Exception {
        var comments = List.of(
                new CommentDto("1", "01.01.2020", "Author1", "Comment content 1", true, "1"),
                new CommentDto("2", "02.02.2020", "Author2", "Comment content 2", false, "1")
        );
        Mockito.when(service.getCommentsByBookId(1L)).thenReturn(comments);
        this.mockMvc
                .perform(get("/api/comment/book/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ALL));
    }

    @DisplayName("возвращать комментарий")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void getComment() throws Exception {
        var comment = new CommentDto("2", "02.02.2020", "Author2", "Comment content 2", false, "1");
        Mockito.when(service.getComment(2L)).thenReturn(comment);
        this.mockMvc
                .perform(get("/api/comment/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ONE));
    }

    @DisplayName("добавлять комментарий для книги")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void addComment() throws Exception {
        var newComment = new CommentDto("0", "05.05.2021", "Author1", "Comment content 3", true, "1");
        var actualComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        Mockito.when(service.addComment(newComment)).thenReturn(actualComment);
        this.mockMvc
                .perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newComment)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_ADD));
    }

    @DisplayName("изменять комментарий")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void updateComment() throws Exception {
        var actualComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        var updatedComment = new CommentDto("1", "10.10.2021", "Author3", "Comment content 5", false, "1");
        Mockito.when(service.getComment(Long.parseLong(actualComment.getId()))).thenReturn(actualComment);
        Mockito.when(service.updateComment(updatedComment)).thenReturn(updatedComment);
        this.mockMvc
                .perform(put("/api/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedComment)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(REQUEST_JSON_UPDATE));
    }

    @DisplayName("удалять комментарий")
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    @Test
    void deleteComment() throws Exception {
        var actualComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        Mockito.when(service.getComment(Long.parseLong(actualComment.getId()))).thenReturn(actualComment);
        this.mockMvc
                .perform(delete("/api/comment/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}