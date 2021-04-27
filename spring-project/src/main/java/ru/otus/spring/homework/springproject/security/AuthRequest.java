package ru.otus.spring.homework.springproject.security;

import lombok.Value;

@Value
public class AuthRequest {
    String userName;
    String password;
}
