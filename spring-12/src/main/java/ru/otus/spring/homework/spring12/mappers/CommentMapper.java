package ru.otus.spring.homework.spring12.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring12.models.dto.CommentDto;
import ru.otus.spring.homework.spring12.models.entity.Comment;
import ru.otus.spring.homework.spring12.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    private final BookRepository bookRepository;

    public CommentMapper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return new CommentDto(
                Long.toString(comment.getId()), comment.getPostDate(), comment.getAuthorName(),
                comment.getContent(), comment.isFavorite(), Long.toString(comment.getBook().getId()));
    }

    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        var book = bookRepository.getOne(Long.parseLong(commentDto.getBookId()));
        return new Comment(
                Long.parseLong(commentDto.getId()), commentDto.getPostDate(), commentDto.getAuthorName(),
                commentDto.getContent(), commentDto.isFavorite(), book
        );
    }

    public List<CommentDto> toDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Comment> toEntityList(List<CommentDto> commentDtoList) {
        return commentDtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
