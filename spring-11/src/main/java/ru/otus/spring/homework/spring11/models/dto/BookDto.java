package ru.otus.spring.homework.spring11.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id;
    private String name;
    private AuthorDto author;
    private List<GenreDto> genres;
    private List<CommentDto> comments;
}
