package ru.otus.spring.homework.spring13.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring13.models.dto.GenreDto;
import ru.otus.spring.homework.spring13.models.entity.Genre;
import ru.otus.spring.homework.spring13.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    private final GenreRepository genreRepository;

    public GenreMapper(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreDto toDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        return new GenreDto(Long.toString(genre.getId()), genre.getName());
    }

    public Genre toEntity(GenreDto genreDto) {
        if (genreDto == null) {
            return null;
        }
        if (!genreDto.getId().isEmpty() && genreDto.getName() == null) {
            var genre = genreRepository.findById(Long.parseLong(genreDto.getId()));
            genre.ifPresent(value -> genreDto.setName(value.getName()));
        }
        return new Genre(Long.parseLong(genreDto.getId()), genreDto.getName());
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
