package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "book")
@Entity
@SequenceGenerator(name = "COMMENT_SEQUENCE", initialValue = 20, allocationSize = 1)
@Table(name = "COMMENTS")
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQUENCE")
    private Long id;

    @Column(name = "POST_DATE", nullable = false)
    private String postDate;

    @Column(name = "AUTHOR_NAME", nullable = false)
    private String authorName;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "FAVORITE")
    private boolean favorite = false;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
