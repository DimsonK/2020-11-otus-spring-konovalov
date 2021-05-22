package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.InstanceDto;

import java.util.List;

public interface InstanceService {

    List<InstanceDto> getAll();

    InstanceDto getInstance(Long instanceId);

    List<InstanceDto> getInstances(List<String> instanceIds);

    List<InstanceDto> getInstancesByBookId(Long bookId);

    InstanceDto addInstance(InstanceDto instanceDto);

    InstanceDto updateInstance(InstanceDto instanceDto);

    void deleteInstance(Long instanceId);

    long getCount();

    boolean instanceExists(Long instanceId);
}
