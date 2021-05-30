package ru.otus.spring.homework.springproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.homework.springproject.models.dto.BookFileDto;

import java.util.List;

public interface BookFileService {

    List<BookFileDto> getAll();

    BookFileDto getBookFile(Long bookFileId);

    BookFileDto getBookFileByBookId(Long bookId);

    void deleteBookFile(Long bookFileId);

    long getCount();

    BookFileDto storeFile(MultipartFile file, Long bookId);

    Resource loadFileAsResource(Long bookFileId);

}
