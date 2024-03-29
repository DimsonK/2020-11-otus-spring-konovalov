package ru.otus.spring.homework.spring12.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework.spring12.models.dto.UserDto;
import ru.otus.spring.homework.spring12.security.AuthRequest;
import ru.otus.spring.homework.spring12.security.AuthService;

import java.util.Objects;

@Slf4j
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> addAuthor(@RequestBody AuthRequest authRequest) {
        if (Objects.isNull(authRequest) || authRequest.getUserName().isBlank() || authRequest.getPassword().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var authentication = authService.authenticate(authRequest.getUserName(), authRequest.getPassword());
        if (authentication.isAuthenticated()) {
            var loggedInUser = (UserDto) authentication.getPrincipal();
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
