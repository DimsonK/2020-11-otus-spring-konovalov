package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.entity.User;

public interface UserService {

    User saveUser(User user);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User getCurrentUser();

}
