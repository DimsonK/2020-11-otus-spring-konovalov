package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.IssueDto;

import java.util.List;

public interface IssueService {

    List<IssueDto> getAll();

    IssueDto getIssue(Long issueId);

    List<IssueDto> getIssues(List<String> issueIds);

    IssueDto addIssue(IssueDto issueDto);

    IssueDto addIssueByOrderId(Long orderId);

    IssueDto updateIssue(IssueDto issueDto);

    void deleteIssue(Long issueId);

    long getCount();
}
