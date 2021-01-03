package ru.otus.spring.homework.spring06.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "author-entity-graph", attributeNodes = @NamedAttributeNode("author"))
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "BOOK_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQUENCE")
    private long id;

    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOK_GENRES", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID", nullable = false, insertable = false, updatable = false)
    private List<Comment> comments = new ArrayList<>();

}
