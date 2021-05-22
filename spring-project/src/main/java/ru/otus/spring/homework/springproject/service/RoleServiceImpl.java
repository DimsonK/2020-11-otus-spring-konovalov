package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public List<RoleDto> getAll() {
        log.debug("getAll()");
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getRole(Long roleId) {
        log.debug("getRole");
        return roleMapper.toDto(roleRepository.getOne(roleId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getRoles(List<String> roleIds) {
        log.debug("getRoles");
        var ids = roleIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return roleMapper.toDtoList(roleRepository.findAllById(ids));
    }

    @Override
    @Transactional
    public RoleDto addRole(RoleDto roleDto) {
        log.debug("addRole");
        var entity = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(roleRepository.save(entity));
    }

    @Override
    @Transactional
    public RoleDto updateRole(RoleDto roleDto) {
        log.debug("updateRole");
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        log.debug("deleteRole");
        roleRepository.deleteById(roleId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        log.debug("getCount");
        return roleRepository.count();
    }
}
