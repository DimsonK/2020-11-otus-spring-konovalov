package ru.otus.spring.homework.springproject.security;

import lombok.Value;

@Value
public class AuthRequest {
    String username;
    String password;
}
