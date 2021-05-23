package ru.otus.spring.homework.springproject.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFileDto {
    private String id;
    private Boolean isActive;
    private String md5;
    private String filePath;
    private String originalName;
    private String filename;
    private String contentType;
    private Long size;
    private String bookId;
}
