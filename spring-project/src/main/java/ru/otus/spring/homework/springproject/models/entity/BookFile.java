package ru.otus.spring.homework.springproject.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "BOOK_FILES_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "BOOK_FILES")
public class BookFile extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_FILES_SEQUENCE")
    private Long id;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "MD5", nullable = false)
    private String md5;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "ORIGINAL_NAME", nullable = false)
    private String originalName;

    @Column(name = "FILENAME", nullable = false)
    private String filename;

    @Column(name = "SIZE", nullable = false)
    private Integer size;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
