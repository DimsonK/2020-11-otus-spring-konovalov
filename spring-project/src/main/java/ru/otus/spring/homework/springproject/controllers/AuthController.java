package ru.otus.spring.homework.springproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.homework.springproject.models.entity.User;
import ru.otus.spring.homework.springproject.security.AuthRequest;
import ru.otus.spring.homework.springproject.security.AuthResponse;
import ru.otus.spring.homework.springproject.security.JwtProvider;
import ru.otus.spring.homework.springproject.security.RegistrationRequest;
import ru.otus.spring.homework.springproject.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class AuthController {

    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;

    public AuthController(UserServiceImpl userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getLogin());
        userService.saveUser(u);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> addAuthor(@RequestBody AuthRequest request) {
        User user = userService.findByUsernameAndPassword(request.getUserName(), request.getPassword());
        if (Objects.nonNull(user)) {
            String token = jwtProvider.generateToken(user.getUsername());
            return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
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
