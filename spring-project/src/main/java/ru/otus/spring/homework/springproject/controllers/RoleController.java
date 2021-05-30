package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.RoleDto;
import ru.otus.spring.homework.springproject.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class RoleController {

    private static final String ROLE_SERVICE = "roleService";

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Получить все роли
    @GetMapping("/api/role")
    @Bulkhead(name = ROLE_SERVICE, fallbackMethod = "bulkHeadGetAllRole", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<List<RoleDto>> getRoles() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

    // Получить роль
    @GetMapping("/api/role/{id}")
    @Bulkhead(name = ROLE_SERVICE, fallbackMethod = "bulkHeadGetRole", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<RoleDto> getRoles(@PathVariable("id") Long roleId) {
        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RoleDto role;
        if (roleId.equals(0L)) {
            role = new RoleDto();
            role.setId("0");
        } else {
            role = roleService.getRole(roleId);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Создать новую роль
    @PostMapping("/api/role")
    @Bulkhead(name = ROLE_SERVICE, fallbackMethod = "bulkHeadGetRole", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        if (roleDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        roleDto.setId("0");
        var role = roleService.addRole(roleDto);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Обновить роль
    @PutMapping("/api/role/{id}")
    @Bulkhead(name = ROLE_SERVICE, fallbackMethod = "bulkHeadGetRole", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        if (roleDto == null || roleDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        roleDto.setId(Long.toString(id));
        var role = roleService.getRole(Long.parseLong(roleDto.getId()));
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        role = roleService.updateRole(roleDto);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Удалить роль
    @DeleteMapping("/api/role/{id}")
    @Bulkhead(name = ROLE_SERVICE, fallbackMethod = "bulkHeadDelRole", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long roleId) {
        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var role = roleService.getRole(roleId);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<RoleDto>> bulkHeadGetAllRole(Exception t) {
        log.info("bulkHeadGetAllRole");
        List<RoleDto> role = new ArrayList<>();
        role.add(new RoleDto("1", "NOBODY"));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    public ResponseEntity<RoleDto> bulkHeadGetRole(Exception t) {
        log.info("bulkHeadGetRole");
        var role = new RoleDto("1", "NOBODY");
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelRole(Exception t) {
        log.info("bulkHeadDelRole");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
