package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.otus.spring.homework.springproject.models.dto.CommentDto;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Comment;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Mapping(source = "book", target = "bookId")
    CommentDto toDto(Comment comment);

    default String mapBookToString(Book book) {
        return book.getId().toString();
    }

    List<CommentDto> toDtoList(List<Comment> comments);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "book", ignore = true)
    Comment toEntity(CommentDto commentDto, @Context BookRepository bookRepository);

    @AfterMapping
    default void toEntity(@MappingTarget Comment target, CommentDto source, @Context BookRepository bookRepository) {
        var book = bookRepository.findById(Long.parseLong(source.getBookId())).orElse(null);
        target.setBook(book);
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    List<Comment> toEntityList(List<CommentDto> commentDtoList);

}
