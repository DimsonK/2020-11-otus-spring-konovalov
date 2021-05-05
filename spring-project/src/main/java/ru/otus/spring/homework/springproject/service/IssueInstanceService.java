package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.IssueInstanceDto;
import ru.otus.spring.homework.springproject.models.entity.IssueInstanceId;

import java.util.List;

public interface IssueInstanceService {

    List<IssueInstanceDto> getAll();

    IssueInstanceDto getIssueInstance(IssueInstanceId issueInstanceId);

    List<IssueInstanceDto> getIssueInstances(List<IssueInstanceId> issueInstanceIds);

    IssueInstanceDto addIssueInstance(IssueInstanceDto issueInstanceName);

    IssueInstanceDto updateIssueInstance(IssueInstanceDto issueInstancesDto);

    void deleteIssueInstances(IssueInstanceId issueInstancesId);

    long getCount();
}
