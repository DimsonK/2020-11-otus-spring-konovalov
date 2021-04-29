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
@SequenceGenerator(name = "GENRE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "GENRES")
public class Genre extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_SEQUENCE")
    private Long id;

    @Column(name = "GENRE_NAME", nullable = false, unique = true)
    private String name;

}
