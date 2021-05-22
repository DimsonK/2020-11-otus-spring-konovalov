package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.service.InstanceService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class InstanceController {
    private static final String INSTANCE_SERVICE = "instanceService";

    private final InstanceService instanceService;

    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    // Получить все экземпляры
    @GetMapping("/api/instance")
    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<InstanceDto>> getInstances() {
        return new ResponseEntity<>(instanceService.getAll(), HttpStatus.OK);
    }

    // Получить экземпляр
    @GetMapping("/api/instance/{id}")
    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<InstanceDto> getInstances(@PathVariable("id") Long instanceId) {
        if (instanceId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        InstanceDto instance;
        if (instanceId.equals(0L)) {
            instance = new InstanceDto();
            instance.setId("0");
        } else {
            instance = instanceService.getInstance(instanceId);
        }
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    // Получить все экземпляры книги
    @GetMapping("/api/instance/book/{bookId}")
    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetAllInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<InstanceDto>> getInstancesByBookId(@PathVariable("bookId") Long bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var instance = instanceService.getInstancesByBookId(bookId);
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    // Создать новый экземпляр
    @PostMapping("/api/instance")
    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<InstanceDto> addInstance(@RequestBody InstanceDto instanceDto) {
        if (instanceDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        instanceDto.setId("0");
        var instance = instanceService.addInstance(instanceDto);
        if (instance == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    // Обновить экземпляр
    @PutMapping("/api/instance/{id}")
//    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadGetInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<InstanceDto> updateInstance(@PathVariable("id") Long id, @RequestBody InstanceDto instanceDto) {
        if (instanceDto == null || instanceDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        instanceDto.setId(Long.toString(id));
        if (!instanceService.instanceExists(Long.parseLong(instanceDto.getId()))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var instance = instanceService.updateInstance(instanceDto);
        if (instance == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    // Удалить экземпляр
    @DeleteMapping("/api/instance/{id}")
    @Bulkhead(name = INSTANCE_SERVICE, fallbackMethod = "bulkHeadDelInstance", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<String> deleteInstance(@PathVariable("id") Long instanceId) {
        if (instanceId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var instance = instanceService.getInstance(instanceId);
        if (instance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        instanceService.deleteInstance(instanceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<InstanceDto>> bulkHeadGetAllInstance(Exception t) {
        log.info("bulkHeadGetAllInstance");
        List<InstanceDto> instance = new ArrayList<>();
        instance.add(new InstanceDto("1", "I9999", "1"));
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    public ResponseEntity<InstanceDto> bulkHeadGetInstance(Exception t) {
        log.info("bulkHeadGetInstance");
        var instance = new InstanceDto("1", "I9999", "1");
        return new ResponseEntity<>(instance, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelInstance(Exception t) {
        log.info("bulkHeadDelInstance");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
