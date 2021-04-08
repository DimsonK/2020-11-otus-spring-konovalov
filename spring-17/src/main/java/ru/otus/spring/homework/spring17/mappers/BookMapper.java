package ru.otus.spring.homework.spring17.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring17.models.dto.BookDto;
import ru.otus.spring.homework.spring17.models.entity.Book;
import ru.otus.spring.homework.spring17.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public BookMapper(AuthorMapper authorMapper, GenreMapper genreMapper, CommentMapper commentMapper,
            CommentRepository commentRepository) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    public BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        var favoriteComments = commentRepository.findByBookAndFavorite(book, true);
        var favoriteCommentsDto = favoriteComments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
        return new BookDto(
                Long.toString(book.getId()), book.getName(), book.getRars(), book.getAccessLevel(),
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
                Long.parseLong(bookDto.getId()), bookDto.getName(), bookDto.getRars(), bookDto.getAccessLevel(),
                authorMapper.toEntity(bookDto.getAuthor()),
                genreMapper.toEntityList(bookDto.getGenres()));
    }

    public List<BookDto> toDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Book> toEntityList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
