package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
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
