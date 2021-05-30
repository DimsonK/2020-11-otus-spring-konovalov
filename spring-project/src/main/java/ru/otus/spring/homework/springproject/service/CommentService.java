package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentsByBookId(Long bookId);

    List<CommentDto> getFavoriteCommentsByBookId(Long bookId);

    CommentDto getComment(Long commentId);

    CommentDto addComment(CommentDto commentDto);

    CommentDto updateComment(CommentDto commentDto);

    void deleteComment(Long commentId);

    long getCount();

}
