package ru.otus.spring.homework.spring08.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Genre {

    @Id
    private String id;

    @NotNull
    private String name;

    public Genre(String name) {
        this.name = name;
    }

}
