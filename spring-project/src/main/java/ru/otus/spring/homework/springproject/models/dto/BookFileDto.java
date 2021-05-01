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
    private String originalFilename;
    private String filename;
    private Integer size;
    private String bookId;
}
