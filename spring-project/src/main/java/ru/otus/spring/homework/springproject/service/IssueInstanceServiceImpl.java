package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.IssueInstanceMapper;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;
import ru.otus.spring.homework.springproject.repositories.IssueInstanceRepository;

import java.util.List;

@Slf4j
@Service
public class IssueInstanceServiceImpl implements IssueInstanceService {

    private final IssueInstanceRepository issueInstanceRepository;
    private final IssueInstanceMapper instanceMapper;

    public IssueInstanceServiceImpl(
        IssueInstanceRepository issueInstanceRepository,
        IssueInstanceMapper instanceMapper
    ) {
        this.issueInstanceRepository = issueInstanceRepository;
        this.instanceMapper = instanceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueInstanceDto> getAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public IssueInstanceDto getIssueInstance(IssueInstanceId issueInstanceId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueInstanceDto> getIssueInstances(List<IssueInstanceId> issueInstanceIds) {
        return null;
    }

    @Override
    @Transactional
    public IssueInstanceDto addIssueInstance(IssueInstanceDto issueInstanceName) {
        return null;
    }

    @Override
    @Transactional
    public IssueInstanceDto updateIssueInstance(IssueInstanceDto issueInstancesDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteIssueInstances(IssueInstanceId issueInstancesId) {

    }

    @Override
    @Transactional
    public long getCount() {
        return issueInstanceRepository.count();
    }
}
