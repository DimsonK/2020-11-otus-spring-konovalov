package ru.otus.spring.homework.spring11.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "book")
@Document
public class Comment {

    @Id
    private String id;

    private String postDate;

    private String authorName;

    private String content;

    private boolean favorite = false;

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
