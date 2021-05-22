package ru.otus.spring.homework.springproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework.springproject.service.IssueInstanceService;

@Slf4j
@RestController
public class IssueInstanceController {

    private static final String ISSUE_INSTANCE_SERVICE = "issueInstanceService";

    private final IssueInstanceService issueInstanceService;

    public IssueInstanceController(IssueInstanceService issueInstanceService) {
        this.issueInstanceService = issueInstanceService;
    }
/*

    // Получить все жанры
    @GetMapping("/api/issueInstance")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<IssueInstanceDto>> getIssueInstances() {
        return new ResponseEntity<>(issueInstanceService.getAll(), HttpStatus.OK);
    }

    // Получить жанр
    @GetMapping("/api/issueInstance/{id}")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueInstanceDto> getIssueInstances(@PathVariable("id") Long issueInstanceId) {
        if (issueInstanceId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        IssueInstanceDto issueInstance;
        if (issueInstanceId.equals(0L)) {
            issueInstance = new IssueInstanceDto();
            issueInstance.setId("0");
        } else {
            issueInstance = issueInstanceService.getIssueInstance(issueInstanceId);
        }
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    // Создать новый жанр
    @PostMapping("/api/issueInstance")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueInstanceDto> addIssueInstance(@RequestBody IssueInstanceDto issueInstanceDto) {
        if (issueInstanceDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        issueInstanceDto.setId("0");
        var issueInstance = issueInstanceService.addIssueInstance(issueInstanceDto);
        if (issueInstance == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    // Обновить жанр
    @PutMapping("/api/issueInstance/{id}")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<IssueInstanceDto> updateIssueInstance(@PathVariable("id") Long id, @RequestBody IssueInstanceDto issueInstanceDto) {
        if (issueInstanceDto == null || issueInstanceDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        issueInstanceDto.setId(Long.toString(id));
        var issueInstance = issueInstanceService.getIssueInstance(Long.parseLong(issueInstanceDto.getId()));
        if (issueInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        issueInstance = issueInstanceService.updateIssueInstance(issueInstanceDto);
        if (issueInstance == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(issueInstance, HttpStatus.OK);
    }

    // Удалить жанр
    @DeleteMapping("/api/issueInstance/{id}")
    @Bulkhead(name = ISSUE_INSTANCE_SERVICE, fallbackMethod = "bulkHeadDelIssueInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<String> deleteIssueInstance(@PathVariable("id") Long issueInstanceId) {
        if (issueInstanceId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var issueInstance = issueInstanceService.getIssueInstance(issueInstanceId);
        if (issueInstance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        issueInstanceService.deleteIssueInstance(issueInstanceId);
        return new ResponseEntity<>(HttpStatus.OK);
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

*/
}
