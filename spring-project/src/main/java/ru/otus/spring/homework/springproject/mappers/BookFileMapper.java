package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.BookFileDto;
import ru.otus.spring.homework.springproject.models.entity.BookFile;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import java.util.List;

@Mapper(uses = BookMapper.class)
public interface BookFileMapper {

    @Mapping(source = "book", target = "bookId")
    BookFileDto toDto(BookFile bookFile);

    List<BookFileDto> toDtoList(List<BookFile> bookFiles);

    @Mapping(source = "bookId", target = "book")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    BookFile toEntity(BookFileDto bookFileDto, @Context BookRepository bookRepository);

    List<BookFile> toEntityList(List<BookFileDto> bookFileDto);

}
