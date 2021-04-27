package ru.otus.spring.homework.springproject.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "ROLE_SEQUENCE", initialValue = 10, allocationSize = 1)
@Table(name = "ROLES")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQUENCE")
    private long id;

    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    private String roleName;

}
