package ru.otus.spring.homework.springproject.models.entity;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "AUTHOR_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "AUTHORS")
public class Author extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQUENCE")
    private Long id;

    @Column(name = "AUTHOR_NAME", nullable = false, unique = true)
    private String name;

}
