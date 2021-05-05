package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.springproject.mappers.IssueInstanceMapper;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;

import java.util.List;

@Slf4j
@Service
public class IssueInstanceServiceImpl implements IssueInstanceService {

    private final IssueInstanceMapper instanceMapper;

    public IssueInstanceServiceImpl(IssueInstanceMapper instanceMapper) {
        this.instanceMapper = instanceMapper;
    }

    @Override
    public List<IssueInstanceDto> getAll() {
        return null;
    }

    @Override
    public IssueInstanceDto getIssueInstance(IssueInstanceId issueInstanceId) {
        return null;
    }

    @Override
    public List<IssueInstanceDto> getIssueInstances(List<IssueInstanceId> issueInstanceIds) {
        return null;
    }

    @Override
    public IssueInstanceDto addIssueInstance(IssueInstanceDto issueInstanceName) {
        return null;
    }

    @Override
    public IssueInstanceDto updateIssueInstance(IssueInstanceDto issueInstancesDto) {
        return null;
    }

    @Override
    public void deleteIssueInstances(IssueInstanceId issueInstancesId) {

    }

    @Override
    public long getCount() {
        return 0;
    }
}
