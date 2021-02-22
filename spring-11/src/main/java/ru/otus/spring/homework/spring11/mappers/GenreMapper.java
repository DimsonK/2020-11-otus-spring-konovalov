package ru.otus.spring.homework.spring11.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring11.models.dto.GenreDto;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreDto toDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        return new GenreDto(genre.getId(), genre.getName());
    }

    public Genre toEntity(GenreDto genreDto) {
        if (genreDto == null) {
            return null;
        }
        return new Genre(genreDto.getId(), genreDto.getName());
    }

    public List<GenreDto> toDtoList(List<Genre> genreList) {
        return genreList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Genre> toEntityList(List<GenreDto> genreDtoList) {
        return genreDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
