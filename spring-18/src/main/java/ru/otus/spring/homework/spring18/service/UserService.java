package ru.otus.spring.homework.spring18.service;

import ru.otus.spring.homework.spring18.models.entity.User;

public interface UserService {

    User findByUsername(String username);

}
