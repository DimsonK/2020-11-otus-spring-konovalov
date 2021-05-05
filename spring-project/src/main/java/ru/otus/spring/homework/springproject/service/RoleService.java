package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto getRole(Long roleId);

    List<RoleDto> getRoles(List<String> roleIds);

    RoleDto addRole(RoleDto roleDto);

    RoleDto updateRole(RoleDto roleDto);

    void deleteRole(Long roleId);

    long getCount();
}
