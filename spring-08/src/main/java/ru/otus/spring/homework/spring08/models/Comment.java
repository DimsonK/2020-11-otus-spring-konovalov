package ru.otus.spring.homework.spring08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "book")
@Document
public class Comment {

    @Id
    private String id;

    @NotNull
    private String postDate;

    @NotNull
    private String authorName;

    @NotNull
    private String content;

    private boolean favorite = false;

    @NotNull
    @DBRef
    private Book book;

    public Comment(String postDate, String authorName, String content, boolean favorite, Book book) {
        this.postDate = postDate;
        this.authorName = authorName;
        this.content = content;
        this.favorite = favorite;
        this.book = book;
    }

}
