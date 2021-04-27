package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.entity.User;

public interface UserService {

    User findByUsername(String username);

}
