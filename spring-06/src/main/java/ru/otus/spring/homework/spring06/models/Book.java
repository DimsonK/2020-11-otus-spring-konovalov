package ru.otus.spring.homework.spring06.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = Book.WITH_AUTHOR_GRAPH, attributeNodes = {
                @NamedAttributeNode("author")
        }),
        @NamedEntityGraph(name = Book.WITH_GENRES_GRAPH, attributeNodes = {
                @NamedAttributeNode("genres")
        }),
        @NamedEntityGraph(name = Book.WITH_AUTHOR_AND_GENRES_GRAPH, attributeNodes = {
                @NamedAttributeNode("author"),
                @NamedAttributeNode("genres")
        }),
        @NamedEntityGraph(name = Book.WITH_COMMENTS_GRAPH, attributeNodes = {
                @NamedAttributeNode(value = "comments", subgraph = "comments-subgraph")
        },
                subgraphs = {@NamedSubgraph(
                        name = "comments-subgraph",
                        attributeNodes = {@NamedAttributeNode("book")})
                })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "BOOK_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "BOOKS")
public class Book {

    public static final String WITH_AUTHOR_GRAPH = "book-with-author-graph";
    public static final String WITH_GENRES_GRAPH = "book-with-genres-graph";
    public static final String WITH_AUTHOR_AND_GENRES_GRAPH = "book-with-author-and-genres-graph";
    public static final String WITH_COMMENTS_GRAPH = "book-with-comments-graph";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQUENCE")
    private long id;

    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String name;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOK_GENRES", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private List<Comment> comments;

}
