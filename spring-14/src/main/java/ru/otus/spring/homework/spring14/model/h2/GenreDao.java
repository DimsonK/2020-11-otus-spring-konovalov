package ru.otus.spring.homework.spring14.model.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDao {
    @Id
    private Long id;
    private String name;
}
