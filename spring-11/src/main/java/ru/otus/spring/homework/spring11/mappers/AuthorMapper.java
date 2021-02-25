package ru.otus.spring.homework.spring11.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring11.models.dto.AuthorDto;
import ru.otus.spring.homework.spring11.models.entity.Author;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author toEntity(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }
        return new Author(authorDto.getId(), authorDto.getName());
    }

    public List<AuthorDto> toDtoList(List<Author> authorList) {
        return authorList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Author> toEntityList(List<AuthorDto> authorDtoList) {
        return authorDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
