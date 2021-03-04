package ru.otus.spring.homework.spring12.service;

import ru.otus.spring.homework.spring12.models.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentsByBookId(long bookId);

    List<CommentDto> getFavoriteCommentsByBookId(long bookId);

    CommentDto getComment(long commentId);

    CommentDto addComment(CommentDto commentDto);

    CommentDto updateComment(CommentDto commentDto);

    void deleteComment(long commentId);

    long getCount();

}
