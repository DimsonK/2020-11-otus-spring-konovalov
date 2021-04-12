package ru.otus.spring.homework.spring17.models.entity;

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
@SequenceGenerator(name = "AUTHOR_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQUENCE")
    private long id;

    @Column(name = "AUTHOR_NAME", nullable = false, unique = true)
    private String name;

}
