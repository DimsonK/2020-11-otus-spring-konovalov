package ru.otus.spring.homework.spring17.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring17.models.dto.UserDto;
import ru.otus.spring.homework.spring17.models.entity.Role;
import ru.otus.spring.homework.spring17.models.entity.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new UserDto(
                user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getAge(), user.getAccessLevel(),
                user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList())
        );
    }
}
