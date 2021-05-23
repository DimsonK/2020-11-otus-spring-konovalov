package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceStatusDto;
import ru.otus.spring.homework.springproject.models.enums.IssueStatus;
import ru.otus.spring.homework.springproject.service.IssueInstanceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class IssueInstanceController {

    private static final String ISSUE_INSTANCE_SERVICE = "issueInstanceService";

    private final IssueInstanceService issueInstanceService;

    public IssueInstanceController(IssueInstanceService issueInstanceService) {
        this.issueInstanceService = issueInstanceService;
    }


    // Получить все выданные экземпляры
    @GetMapping("/api/issue_instance")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<IssueInstanceDto>> getIssueInstances() {
        return new ResponseEntity<>(issueInstanceService.getAll(), HttpStatus.OK);
    }

    // Получить экземпляры по id выдачи
    @GetMapping("/api/issue_instance/issue/{issueId}")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<IssueInstanceDto>> getInstancesByIssueId(@PathVariable("issueId") Long issueId) {
        if (issueId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var issueInstance = issueInstanceService.getInstancesByIssueId(issueId);
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    // Получить экземпляры по id выдачи
    @GetMapping("/api/issue_instance/user/{userId}")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<IssueInstanceDto>> getInstancesByUserId(@PathVariable("userId") Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var issueInstance = issueInstanceService.getInstancesByUserId(userId);
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    // Обновить статус выдачи
    @PutMapping("/api/issue_instance/status")
    public ResponseEntity<String> updateStatusIssueInstance(@RequestBody IssueInstanceStatusDto issueInstanceStatusDto) {
        issueInstanceService.setStatus(issueInstanceStatusDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<List<IssueInstanceDto>> bulkHeadGetAllIssueInstance(Exception t) {
        log.info("bulkHeadGetAllIssueInstance");
        List<IssueInstanceDto> issueInstance = new ArrayList<>();
        var issueDto = new IssueDto("1", LocalDateTime.now(), "1", "1");
        var instanceDto = new InstanceDto("1", "I9999", "1");
        issueInstance.add(new IssueInstanceDto(issueDto, instanceDto, IssueStatus.ISSUED, null));
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    public ResponseEntity<IssueInstanceDto> bulkHeadGetIssueInstance(Exception t) {
        log.info("bulkHeadGetIssueInstance");
        var issueDto = new IssueDto("1", LocalDateTime.now(), "1", "1");
        var instanceDto = new InstanceDto("1", "I9999", "1");
        var issueInstance = new IssueInstanceDto(issueDto, instanceDto, IssueStatus.ISSUED, null);
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelIssueInstance(Exception t) {
        log.info("bulkHeadDelIssueInstance");
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
