package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.springproject.mappers.InstanceMapper;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.repositories.InstanceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstanceServiceImpl implements InstanceService {

    private final InstanceRepository instanceRepository;
    private final InstanceMapper instanceMapper;

    public InstanceServiceImpl(
        InstanceRepository instanceRepository,
        InstanceMapper instanceMapper
    ) {
        this.instanceRepository = instanceRepository;
        this.instanceMapper = instanceMapper;
    }

    @Override
    public List<InstanceDto> getAll() {
        log.debug("getAllInstances");
        return instanceMapper.toDtoList(instanceRepository.findAll());
    }

    @Override
    public InstanceDto getInstance(Long instanceId) {
        log.debug("getInstance");
        return instanceMapper.toDto(instanceRepository.getOne(instanceId));
    }

    @Override
    public List<InstanceDto> getInstances(List<String> instanceIds) {
        log.debug("getInstances");
        var ids = instanceIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return instanceMapper.toDtoList(instanceRepository.findAllById(ids));
    }

    @Override
    public InstanceDto addInstance(InstanceDto instanceDto) {
        log.debug("addInstance");
        var entity = instanceMapper.toEntity(instanceDto);
        return instanceMapper.toDto(instanceRepository.save(entity));
    }

    @Override
    public InstanceDto updateInstance(InstanceDto instanceDto) {
        log.debug("updateInstance");
        return instanceMapper.toDto(instanceRepository.save(instanceMapper.toEntity(instanceDto)));
    }

    @Override
    public void deleteInstance(Long instanceId) {
        log.debug("deleteInstance");
        instanceRepository.deleteById(instanceId);
    }

    @Override
    public long getCount() {
        log.debug("getCount");
        return instanceRepository.count();
    }
}
