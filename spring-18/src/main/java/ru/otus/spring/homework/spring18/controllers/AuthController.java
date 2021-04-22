package ru.otus.spring.homework.spring18.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework.spring18.models.dto.UserDto;
import ru.otus.spring.homework.spring18.security.AuthRequest;
import ru.otus.spring.homework.spring18.security.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

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

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
