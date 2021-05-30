package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.spring.homework.springproject.models.dto.UserDto;
import ru.otus.spring.homework.springproject.models.entity.User;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.util.List;

@Mapper(uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "roles", target = "roles")
    UserDto toDto(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDto);

    default String userToString(User user) {
        return user.getId().toString();
    }

    default User userFromUserId(String userId, @Context UserRepository userRepository) {
        return userRepository.findById(Long.parseLong(userId)).orElse(null);
    }

    ;

}
