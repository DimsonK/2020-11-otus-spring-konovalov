package ru.otus.spring.homework.springproject.security;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
