package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.homework.springproject.config.AppProperties;
import ru.otus.spring.homework.springproject.exceptions.BadRequestException;
import ru.otus.spring.homework.springproject.exceptions.FileStorageException;
import ru.otus.spring.homework.springproject.mappers.BookFileMapper;
import ru.otus.spring.homework.springproject.models.dto.BookFileDto;
import ru.otus.spring.homework.springproject.models.entity.BookFile;
import ru.otus.spring.homework.springproject.repositories.BookFileRepository;
import ru.otus.spring.homework.springproject.repositories.BookRepository;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BookFileServiceImpl implements BookFileService {

    private static final String FILE_NOT_FOUND = "File not found";

    private final AppProperties properties;
    private final BookFileRepository bookFileRepository;
    private final BookRepository bookRepository;
    private final BookFileMapper bookFileMapper;

    public BookFileServiceImpl(
        AppProperties properties,
        BookFileRepository bookFileRepository,
        BookRepository bookRepository,
        BookFileMapper bookFileMapper
    ) {
        this.properties = properties;
        this.bookFileRepository = bookFileRepository;
        this.bookRepository = bookRepository;
        this.bookFileMapper = bookFileMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<BookFileDto> getAll() {
        log.debug("getAll()");
        return bookFileMapper.toDtoList(bookFileRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public BookFileDto getBookFile(Long bookFileId) {
        log.debug("getBookFile");
        return bookFileMapper.toDto(bookFileRepository.findById(bookFileId).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public BookFileDto getBookFileByBookId(Long bookId) {
        log.debug("getBookFileByBookId");
        var book = bookRepository.findById(bookId).orElse(null);
        if (Objects.isNull(book)) {
            throw new BadRequestException(String.format("book with id: %s not found", bookId));
        }
        return bookFileMapper.toDto(Optional.of(bookFileRepository.findBookFileByBook(book)).orElse(null));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteBookFile(Long bookFileId) {
        log.debug("deleteBookFile");
        var bookFile = bookFileRepository.findById(bookFileId).orElse(null);
        if (Objects.isNull(bookFile)) {
            throw new BadRequestException(String.format("BookFile with id: %s not found", bookFileId));
        }
        var file = new File(bookFile.getFilePath());
        if (file.exists()) {
            var filePath = Paths.get(bookFile.getFilePath()).toAbsolutePath().normalize();
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bookFileRepository.delete(bookFile);
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public long getCount() {
        return bookFileRepository.count();
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BookFileDto storeFile(MultipartFile file, Long bookId) {

        var book = bookRepository.findById(bookId).orElse(null);
        if (Objects.isNull(book)) {
            throw new BadRequestException("book not found");
        }

        // get store filepath
        var path = String.format("%s/%s", properties.getFileStorePath(), getFilePath());
        var directory = new File(path);
        if (!directory.exists()) {
            var createDirs = directory.mkdirs();
            if (!createDirs) {
                throw new BadRequestException("directory not created");
            }
        }

        // get store filename
        var uuid = UUID.randomUUID().toString();
        var originalFilename = file.getOriginalFilename();
        var storeFilename = "";
        if (Objects.nonNull(originalFilename)) {
            var tokens = originalFilename.split("\\.");
            if (tokens.length > 0) {
                storeFilename = uuid.concat("." + tokens[tokens.length - 1]);
            } else {
                storeFilename = uuid.concat(".ext");
            }
        }

        // write file
        var storeFile = new File(String.format("%s/%s", path, storeFilename));
        try (var outputStream = new FileOutputStream(storeFile)) {
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get md5
        var md5 = "";
        try {
            var md = MessageDigest.getInstance("MD5");
            md.update(file.getBytes());
            var digest = md.digest();
            md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        // create and save object
        var entity = new BookFile(
            null, true, md5, storeFile.getPath(), file.getOriginalFilename(),
            storeFilename, file.getContentType(), file.getSize(), book
        );
        return bookFileMapper.toDto(bookFileRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Resource loadFileAsResource(Long bookFileId) {
        var bookFile = bookFileRepository.findById(bookFileId).orElse(null);
        if (Objects.isNull(bookFile)) {
            throw new BadRequestException("BookFile not found");
        }

        var filePath = Paths.get(bookFile.getFilePath()).toAbsolutePath().normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new FileStorageException(FILE_NOT_FOUND);
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException(FILE_NOT_FOUND);
        }

        // get md5
        var md5 = "";
        try {
            var md = MessageDigest.getInstance("MD5");
            md.update(resource.getInputStream().readAllBytes());
            var digest = md.digest();
            md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new FileStorageException(FILE_NOT_FOUND);
        }

        // check md5
        if (!md5.equals(bookFile.getMd5())) {
            throw new FileStorageException("file md5 error");
        }

        return resource;
    }

    private String getFilePath() {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        var now = LocalDateTime.now();
        return String.format("/%s", now.format(formatter));
    }

}
