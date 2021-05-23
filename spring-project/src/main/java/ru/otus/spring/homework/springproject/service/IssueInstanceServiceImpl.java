package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.exceptions.BadRequestException;
import ru.otus.spring.homework.springproject.mappers.IssueInstanceMapper;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.dto.IssueInstanceStatusDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;
import ru.otus.spring.homework.springproject.repositories.IssueInstanceRepository;
import ru.otus.spring.homework.springproject.repositories.IssueRepository;
import ru.otus.spring.homework.springproject.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IssueInstanceServiceImpl implements IssueInstanceService {

    private final IssueInstanceRepository issueInstanceRepository;
    private final IssueInstanceMapper issueInstanceMapper;
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;

    public IssueInstanceServiceImpl(
        IssueInstanceRepository issueInstanceRepository,
        IssueInstanceMapper issueInstanceMapper,
        IssueRepository issueRepository, UserRepository userRepository) {
        this.issueInstanceRepository = issueInstanceRepository;
        this.issueInstanceMapper = issueInstanceMapper;
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueInstanceDto> getAll() {
        return issueInstanceMapper.toDtoList(issueInstanceRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueInstanceDto> getInstancesByIssueId(Long issueId) {
        var issue = issueRepository.findById(issueId).orElse(null);
        if (Objects.isNull(issue)) {
            throw new BadRequestException(String.format("Issue with id %s not found", issueId));
        }
        return issueInstanceMapper.toDtoList(issueInstanceRepository.findIssueInstanceByIssue(issue));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueInstanceDto> getInstancesByUserId(Long userId) {
        var user = userRepository.findById(userId).orElse(null);
        if (Objects.isNull(user)) {
            throw new BadRequestException(String.format("User with id %s not found", userId));
        }
        var issues = issueRepository.findIssueByUser(user);
        return issues.stream()
            .map(issue -> issueInstanceMapper.toDtoList(issueInstanceRepository.findIssueInstanceByIssue(issue)))
            .collect(Collectors.toList())
            .stream().flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setStatus(IssueInstanceStatusDto data) {
        var id = new IssueInstanceId(Long.parseLong(data.getIssueId()), Long.parseLong(data.getInstanceId()));
        var issueInstance = issueInstanceRepository.findById(id).orElse(null);
        if (Objects.isNull(issueInstance)) {
            throw new BadRequestException("IssueInstance not found");
        }
        issueInstance.setStatus(data.getStatus());
        issueInstanceRepository.save(issueInstance);
    }

    @Override
    @Transactional
    public long getCount() {
        return issueInstanceRepository.count();
    }
}
