package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.exceptions.BadRequestException;
import ru.otus.spring.homework.springproject.mappers.InstanceMapper;
import ru.otus.spring.homework.springproject.models.dto.InstanceDto;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.InstanceRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstanceServiceImpl implements InstanceService {

    private final InstanceRepository instanceRepository;
    private final InstanceMapper instanceMapper;
    private final BookRepository bookRepository;

    public InstanceServiceImpl(
        InstanceRepository instanceRepository,
        InstanceMapper instanceMapper,
        BookRepository bookRepository
    ) {
        this.instanceRepository = instanceRepository;
        this.instanceMapper = instanceMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<InstanceDto> getAll() {
        log.debug("getAllInstances");
        return instanceMapper.toDtoList(instanceRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public InstanceDto getInstance(Long instanceId) {
        log.debug("getInstance");
        return instanceMapper.toDto(instanceRepository.findById(instanceId).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<InstanceDto> getInstances(List<String> instanceIds) {
        log.debug("getInstances");
        var ids = instanceIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return instanceMapper.toDtoList(instanceRepository.findAllById(ids));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<InstanceDto> getInstancesByBookId(Long bookId) {
        log.debug("getInstancesByBookId");
        var book = bookRepository.findById(bookId).orElse(null);
        var instances = instanceRepository.getInstanceByBook(book);
        return instanceMapper.toDtoList(instances);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public InstanceDto addInstance(InstanceDto instanceDto) {
        log.debug("addInstance");
        var entity = instanceMapper.toEntity(instanceDto, bookRepository);
        return instanceMapper.toDto(instanceRepository.save(entity));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public InstanceDto updateInstance(InstanceDto instanceDto) {
        log.debug("updateInstance");
        var instance = instanceRepository.findById(Long.parseLong(instanceDto.getId())).orElse(null);
        if (Objects.isNull(instance)) {
            throw new BadRequestException("Instance not found");
        }
        instance.setInventoryNumber(instanceDto.getInventoryNumber());
        return instanceMapper.toDto(instanceRepository.save(instance));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteInstance(Long instanceId) {
        log.debug("deleteInstance");
        instanceRepository.deleteById(instanceId);
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public long getCount() {
        log.debug("getCount");
        return instanceRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public boolean instanceExists(Long instanceId) {
        log.debug("instanceExists");
        return instanceRepository.existsById(instanceId);
    }


}
