package ru.otus.spring.homework.springproject.mappers;

import org.mapstruct.*;
import ru.otus.spring.homework.springproject.models.dto.UserDto;
import ru.otus.spring.homework.springproject.models.entity.Role;
import ru.otus.spring.homework.springproject.models.entity.User;
import ru.otus.spring.homework.springproject.repositories.RoleRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    @Mapping(source = "roles", target = "roles")
    UserDto toDto(User user);

    default String mapRoleToString(Role role) {
        return Objects.isNull(role) ? null : role.getRoleName();
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserDto userDto, @Context RoleRepository roleRepository);

    @AfterMapping
    default void fillRoles(UserDto source, @MappingTarget User target, @Context RoleRepository roleRepository) {
        var roles = source.getRoles().stream()
            .map(roleName -> roleRepository.findByRoleName(roleName).orElse(null))
            .collect(Collectors.toList());
        target.setRoles(roles);
    }

    List<UserDto> toDtoList(List<User> users);

//    List<User> toEntityList(List<UserDto> userDto);

}
