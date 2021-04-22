package ru.otus.spring.homework.spring18.security;

import lombok.Value;

@Value
public class AuthRequest {
    String userName;
    String password;
}
