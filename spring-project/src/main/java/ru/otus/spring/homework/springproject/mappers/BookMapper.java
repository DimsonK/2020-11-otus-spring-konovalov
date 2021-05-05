package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.*;
import ru.otus.spring.homework.springproject.models.dto.BookDto;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = {AuthorMapper.class, GenreMapper.class, CommentService.class})
public interface BookMapper {

    @Mapping(target = "comments", ignore = true)
    BookDto toDto(Book book, @Context CommentService commentService);

    @AfterMapping
    default void fillComments(Book source, @MappingTarget BookDto target, @Context CommentService commentService) {
        target.setComments(commentService.getFavoriteCommentsByBookId(source.getId()));
    }

    List<BookDto> toDtoList(List<Book> books, @Context CommentService commentService);

    default List<BookDto> toBookDto(List<Book> books, @Context CommentService commentService) {
        return books.stream()
            .map(book -> toDto(book, commentService))
            .collect(Collectors.toList());
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "instances", ignore = true)
    Book toEntity(BookDto bookDto);

    List<Book> toEntityList(List<BookDto> bookDtoList);

}
