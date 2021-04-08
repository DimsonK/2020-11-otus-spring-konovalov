package ru.otus.spring.homework.spring17.security;

import lombok.Value;

@Value
public class AuthRequest {
    String userName;
    String password;
}
