package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.homework.springproject.models.dto.BookFileDto;
import ru.otus.spring.homework.springproject.service.BookFileService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BookFileController {

    private static final String BOOK_FILE_SERVICE = "bookFileService";

    private final BookFileService bookFileService;

    public BookFileController(BookFileService bookFileService) {
        this.bookFileService = bookFileService;
    }

    // Получить все файлы
    @GetMapping("/api/book_file")
    @Bulkhead(name = BOOK_FILE_SERVICE, fallbackMethod = "bulkHeadGetAllBookFile", type = Type.SEMAPHORE)
    public ResponseEntity<List<BookFileDto>> getBookFiles() {
        return new ResponseEntity<>(bookFileService.getAll(), HttpStatus.OK);
    }

    // Получить файл
    @GetMapping("/api/book_file/{id}")
    @Bulkhead(name = BOOK_FILE_SERVICE, fallbackMethod = "bulkHeadGetBookFile", type = Type.SEMAPHORE)
    public ResponseEntity<BookFileDto> getBookFiles(@PathVariable("id") Long bookFileId) {
        if (bookFileId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BookFileDto bookFile;
        if (bookFileId.equals(0L)) {
            bookFile = new BookFileDto();
            bookFile.setId("0");
        } else {
            bookFile = bookFileService.getBookFile(bookFileId);
        }
        return new ResponseEntity<>(bookFile, HttpStatus.OK);
    }

    // add book file
    @PostMapping("/api/book_file/upload/{bookId}")
    public ResponseEntity<BookFileDto> uploadFile(
        @PathVariable("bookId") Long bookId, @RequestParam("file") MultipartFile file) {

        var bookFileDto = bookFileService.storeFile(file, bookId);
        return new ResponseEntity<>(bookFileDto, HttpStatus.OK);
    }

    // download book file
    @GetMapping("/api/book_file/download/{bookFileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("bookFileId") Long bookFileId, HttpServletRequest request) {
        var bookFile = bookFileService.getBookFile(bookFileId);
        var resource = bookFileService.loadFileAsResource(bookFileId);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(bookFile.getContentType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", bookFile.getOriginalName()))
            .body(resource);
    }

    // Удалить файл
    @DeleteMapping("/api/book_file/{id}")
    @Bulkhead(name = BOOK_FILE_SERVICE, fallbackMethod = "bulkHeadDelBookFile", type = Type.SEMAPHORE)
    public ResponseEntity<String> deleteBookFile(@PathVariable("id") Long bookFileId) {
        if (bookFileId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var bookFile = bookFileService.getBookFile(bookFileId);
        if (bookFile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookFileService.deleteBookFile(bookFileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<BookFileDto>> bulkHeadGetAllBookFile(Exception t) {
        log.info("bulkHeadGetAllBookFile");
        List<BookFileDto> bookFile = new ArrayList<>();
        bookFile.add(new BookFileDto());
        return new ResponseEntity<>(bookFile, HttpStatus.OK);
    }

    public ResponseEntity<BookFileDto> bulkHeadGetBookFile(Exception t) {
        log.info("bulkHeadGetBookFile");
        var bookFile = new BookFileDto();
        return new ResponseEntity<>(bookFile, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelBookFile(Exception t) {
        log.info("bulkHeadDelBookFile");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
