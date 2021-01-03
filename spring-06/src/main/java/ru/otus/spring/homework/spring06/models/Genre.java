package ru.otus.spring.homework.spring06.models;

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
@SequenceGenerator(name = "GENRE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_SEQUENCE")
    private long id;

    @Column(name = "GENRE_NAME", nullable = false, unique = true)
    private String name;

}
