package ru.otus.spring.homework.spring11.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private String id;

    private String name;

    private Author author;

    private List<Genre> genres;

    public Book(String name, Author author, Genre... genres) {
        this.name = name;
        this.author = author;
        this.genres = Arrays.asList(genres);
    }

}
