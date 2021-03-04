package ru.otus.spring.homework.spring12.mappers;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework.spring12.models.dto.UserDto;
import ru.otus.spring.homework.spring12.models.entity.User;

import java.util.Objects;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new UserDto(user.getUsername(), user.getFirstName(), user.getLastName());
    }
}
