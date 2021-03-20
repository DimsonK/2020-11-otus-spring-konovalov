package ru.otus.spring.homework.spring14.model.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenresDao {
    private Long bookId;
    private Long genreId;
}
