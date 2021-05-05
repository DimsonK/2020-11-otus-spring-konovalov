package ru.otus.spring.homework.springproject.security;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class JwtPayload {
    String id;
    String username;
    String email;
    List<String> roles;
    String jti;
    Date iat;
    Date exp;
}
