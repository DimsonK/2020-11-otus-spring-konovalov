package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.GenreDto;
import ru.otus.spring.homework.springproject.models.entity.Genre;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    List<GenreDto> toDtoList(List<Genre> genres);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Genre toEntity(GenreDto genreDto);

    List<Genre> toEntityList(List<GenreDto> genreDto);
}
