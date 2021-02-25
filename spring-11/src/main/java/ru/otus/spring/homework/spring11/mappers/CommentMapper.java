package ru.otus.spring.homework.spring11.mappers;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring11.models.dto.BookDto;
import ru.otus.spring.homework.spring11.models.dto.CommentDto;
import ru.otus.spring.homework.spring11.models.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    private final BookMapper bookMapper;

    public CommentMapper(@Lazy BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return new CommentDto(
                comment.getId(), comment.getPostDate(), comment.getAuthorName(),
                comment.getContent(), comment.isFavorite(), comment.getBook().getId());
    }

    public Comment toEntity(CommentDto commentDto, BookDto bookDto) {
        if (commentDto == null) {
            return null;
        }
        return new Comment(commentDto.getId(), commentDto.getPostDate(), commentDto.getAuthorName(),
                commentDto.getContent(), commentDto.isFavorite(), bookMapper.toEntity(bookDto));
    }

    public List<CommentDto> toDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Comment> toEntityList(List<CommentDto> commentDtoList, BookDto bookDto) {
        return commentDtoList.stream()
                .map(comment -> this.toEntity(comment, bookDto))
                .collect(Collectors.toList());
    }

}
