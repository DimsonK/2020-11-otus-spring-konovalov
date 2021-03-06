package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;
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
    })
})
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orders", "instances"})
@Entity
@SequenceGenerator(name = "BOOK_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "BOOKS")
public class Book extends AuditModel {

    public static final String WITH_AUTHOR_GRAPH = "book-with-author-graph";
    public static final String WITH_GENRES_GRAPH = "book-with-genres-graph";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQUENCE")
    private Long id;

    @Column(name = "BOOK_NAME", nullable = false, unique = true)
    private String name;

    // Аннотация, ISBN, Издательство, Серия, Объём, Масса, Размеры(высота, ширина, толщина), см
    // Изображения[]

    // Возрастная классификация
    @Column(name = "RARS", nullable = false)
    private Integer rars;

    // Уровень доступа для просмотра книги
    @Column(name = "ACCESS_LEVEL", nullable = false)
    private Integer accessLevel;

    @Fetch(FetchMode.JOIN)
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOK_GENRES", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "BOOK_ORDERS", joinColumns = @JoinColumn(name = "BOOK_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
    private List<Order> orders;

    @OneToMany(targetEntity = Instance.class, mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Instance> instances;

}
