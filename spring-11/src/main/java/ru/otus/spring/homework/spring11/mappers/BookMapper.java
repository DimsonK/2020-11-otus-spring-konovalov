package ru.otus.spring.homework.spring11.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring11.models.dto.BookDto;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Comment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final CommentMapper commentMapper;

    public BookMapper(
            AuthorMapper authorMapper,
            GenreMapper genreMapper,
            CommentMapper commentMapper
    ) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.commentMapper = commentMapper;
    }

    public BookDto toDto(Book book, List<Comment> favoriteComments) {
        if (book == null) {
            return null;
        }
        var favoriteCommentsDto = favoriteComments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        return new BookDto(
                book.getId(), book.getName(),
                authorMapper.toDto(book.getAuthor()),
                genreMapper.toDtoList(book.getGenres()),
                favoriteCommentsDto
        );
    }

    public Book toEntity(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        return new Book(
                bookDto.getId(), bookDto.getName(),
                authorMapper.toEntity(bookDto.getAuthor()),
                genreMapper.toEntityList(bookDto.getGenres()));
    }

    public List<BookDto> toDtoList(List<Book> bookList, Map<String, List<Comment>> comments) {
        return bookList.stream()
                .map(book -> {
                    var bookComments = comments.get(book.getId());
                    return this.toDto(book, bookComments);
                })
                .collect(Collectors.toList());
    }

    public List<Book> toEntityList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
