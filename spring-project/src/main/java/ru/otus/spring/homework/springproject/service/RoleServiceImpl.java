package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.springproject.mappers.RoleMapper;
import ru.otus.spring.homework.springproject.models.dto.RoleDto;
import ru.otus.spring.homework.springproject.repositories.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(
        RoleRepository roleRepository,
        RoleMapper roleMapper
    ) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> getAll() {
        log.debug("getAll()");
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    @Override
    public RoleDto getRole(Long roleId) {
        log.debug("getRole");
        return roleMapper.toDto(roleRepository.getOne(roleId));
    }

    @Override
    public List<RoleDto> getRoles(List<String> roleIds) {
        log.debug("getRoles");
        var ids = roleIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return roleMapper.toDtoList(roleRepository.findAllById(ids));
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        log.debug("addRole");
        var entity = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(roleRepository.save(entity));
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        log.debug("updateRole");
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }

    @Override
    public void deleteRole(Long roleId) {
        log.debug("deleteRole");
        roleRepository.deleteById(roleId);
    }

    @Override
    public long getCount() {
        log.debug("getCount");
        return roleRepository.count();
    }
}
