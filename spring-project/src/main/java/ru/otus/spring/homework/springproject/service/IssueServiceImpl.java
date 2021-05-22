package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.IssueMapper;
import ru.otus.spring.homework.springproject.models.dto.IssueDto;
import ru.otus.spring.homework.springproject.repositories.IssueRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;

    public IssueServiceImpl(
        IssueRepository issueRepository,
        IssueMapper issueMapper
    ) {
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueDto> getAll() {
        log.debug("getAllIssue");
        return issueMapper.toDtoList(issueRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public IssueDto getIssue(Long issueId) {
        log.debug("getIssue");
        return issueMapper.toDto(issueRepository.getOne(issueId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueDto> getIssues(List<String> issueIds) {
        log.debug("getIssues");
        var ids = issueIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return issueMapper.toDtoList(issueRepository.findAllById(ids));
    }

    @Override
    @Transactional
    public IssueDto addIssue(IssueDto issueDto) {
        log.debug("addIssue");
        var entity = issueMapper.toEntity(issueDto);
        return issueMapper.toDto(issueRepository.save(entity));
    }

    @Override
    @Transactional
    public IssueDto updateIssue(IssueDto issueDto) {
        log.debug("updateIssue");
        return issueMapper.toDto(issueRepository.save(issueMapper.toEntity(issueDto)));
    }

    @Override
    @Transactional
    public void deleteIssue(Long issueId) {
        log.debug("deleteIssue");
        issueRepository.deleteById(issueId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        return issueRepository.count();
    }
}
