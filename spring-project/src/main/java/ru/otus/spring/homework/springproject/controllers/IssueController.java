package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.service.IssueService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class IssueController {

    private static final String ISSUE_SERVICE = "issueService";

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    // Получить все выдачи
    @GetMapping("/api/issue")
    @Bulkhead(name = ISSUE_SERVICE, fallbackMethod = "bulkHeadGetAllIssue", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<IssueDto>> getIssues() {
        return new ResponseEntity<>(issueService.getAll(), HttpStatus.OK);
    }

    // Получить выдача
    @GetMapping("/api/issue/{id}")
    @Bulkhead(name = ISSUE_SERVICE, fallbackMethod = "bulkHeadGetIssue", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueDto> getIssues(@PathVariable("id") Long issueId) {
        if (issueId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        IssueDto issue;
        if (issueId.equals(0L)) {
            issue = new IssueDto();
            issue.setId("0");
        } else {
            issue = issueService.getIssue(issueId);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    // Создать новый выдача
    @PostMapping("/api/issue")
    @Bulkhead(name = ISSUE_SERVICE, fallbackMethod = "bulkHeadGetIssue", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueDto> addIssue(@RequestBody IssueDto issueDto) {
        if (issueDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        issueDto.setId("0");
        var issue = issueService.addIssue(issueDto);
        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    // Обновить выдача
    @PutMapping("/api/issue/{id}")
    @Bulkhead(name = ISSUE_SERVICE, fallbackMethod = "bulkHeadGetIssue", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueDto> updateIssue(@PathVariable("id") Long id, @RequestBody IssueDto issueDto) {
        if (issueDto == null || issueDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        issueDto.setId(Long.toString(id));
        var issue = issueService.getIssue(Long.parseLong(issueDto.getId()));
        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        issue = issueService.updateIssue(issueDto);
        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    // Удалить выдачу
    @DeleteMapping("/api/issue/{id}")
    @Bulkhead(name = ISSUE_SERVICE, fallbackMethod = "bulkHeadDelIssue", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<String> deleteIssue(@PathVariable("id") Long issueId) {
        if (issueId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var issue = issueService.getIssue(issueId);
        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        issueService.deleteIssue(issueId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<IssueDto>> bulkHeadGetAllIssue(Exception t) {
        log.info("bulkHeadGetAllIssue");
        List<IssueDto> issue = new ArrayList<>();
        issue.add(new IssueDto("1", LocalDateTime.now(), "1", "1"));
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    public ResponseEntity<IssueDto> bulkHeadGetIssue(Exception t) {
        log.info("bulkHeadGetIssue");
        var issue = new IssueDto("1", LocalDateTime.now(), "1", "1");
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelIssue(Exception t) {
        log.info("bulkHeadDelIssue");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
