package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.UserDto;
import ru.otus.spring.homework.springproject.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAll();

    UserDto getUser(Long userId);

    List<UserDto> getUsers(List<String> userIds);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long userId);

    long getCount();

    User saveUser(User user);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    Optional<String> getCurrentUser();

}
