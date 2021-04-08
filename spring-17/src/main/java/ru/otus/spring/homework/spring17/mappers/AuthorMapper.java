package ru.otus.spring.homework.spring17.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring17.models.dto.AuthorDto;
import ru.otus.spring.homework.spring17.models.entity.Author;
import ru.otus.spring.homework.spring17.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    private final AuthorRepository authorRepository;

    public AuthorMapper(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDto(Long.toString(author.getId()), author.getName());
    }

    public Author toEntity(AuthorDto authorDto) {
        if (authorDto == null) {
            return null;
        }
        if (!authorDto.getId().isEmpty() && authorDto.getName() == null) {
            var author = authorRepository.findById(Long.parseLong(authorDto.getId()));
            author.ifPresent(value -> authorDto.setName(value.getName()));
        }
        return new Author(Long.parseLong(authorDto.getId()), authorDto.getName());
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
